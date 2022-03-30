package com.gamerssite.gamerssite.services;

import com.gamerssite.gamerssite.entity.Game;
import com.gamerssite.gamerssite.repositories.GameRepository;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.io.IOException;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;

    @Transactional
    public String addNewGameByUrlController(String url, Model model) {
        if (!url.matches("https://store.steampowered.com/app/\\d+/.+/")) {
            model.addAttribute("gameAddError", "Invalid link");
            return "game-add";
        }
        if (gameRepository.findByLink(url).isPresent()) {
            model.addAttribute("gameAddError", "This game already exist in game pool");
            return "game-add";
        }

        try {
            Game newGameData = parseHtml(url);

            System.out.println(newGameData.toString());

            return "redirect:/";
        } catch (IOException e) {
            System.out.println("HTML parse error");
            model.addAttribute("gameAddError", "Parse error");
            return "game-add";
        }
    }

    private Game parseHtml(String url) throws IOException {
        Document page = Jsoup.connect(url).get();

        String title = page.getElementById("appHubAppName").text();
        String imageUrl = page.getElementById("gameHeaderImageCtn").child(0).attr("src");
        Boolean isEarlyAccess = page.getElementById("earlyAccessHeader") != null;
        Integer price = page.getElementsByClass("game_purchase_price price").get(0).text()
                .replaceAll("[^\\d]", "")
                .transform(new Function<String, Integer>() {
                    @Override
                    public Integer apply(String s) {
                        return s.equals("") ? 0 : Integer.parseInt(s);
                    }
                });

        ////// PC CONFIG //////
        Elements systemRecs = page.getElementsByClass("game_area_sys_req sysreq_content active")
                .get(0)
                .child(0).getElementsByTag("ul")
                .get(0)
                .getElementsByTag("ul")
                .get(0)
                .getElementsByTag("li");

        String graphicCard = systemRecs.stream()
                .filter(element -> element.getElementsByTag("strong").text().equals("Graphics:"))
                .findFirst().map(element -> element.text().replaceAll("Graphics: ", "")).orElse(null);
        String processor = systemRecs.stream()
                .filter(element -> element.getElementsByTag("strong").text().equals("Processor:"))
                .findFirst().map(element -> element.text().replaceAll("Processor: ", "")).orElse(null);
        String ram = systemRecs.stream()
                .filter(element -> element.getElementsByTag("strong").text().equals("Memory:"))
                .findFirst().map(element -> element.text().replaceAll("Memory: ", "")).orElse(null);
        String diskMem = systemRecs.stream()
                .filter(element -> element.getElementsByTag("strong").text().equals("Storage:"))
                .findFirst().map(element -> element.text().replaceAll("Storage: ", "")).orElse(null);

        return Game.builder()
                .title(title)
                .rating(0.0)
                .link(url)
                .image(imageUrl)
                .isEarlyAccess(isEarlyAccess)
                .price(price)
                .minGraphicCard(graphicCard)
                .minProcessor(processor)
                .minRam(ram)
                .diskMemory(diskMem)
                .build();
    }
}
