package com.gamerssite.gamerssite.services.game;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamerssite.gamerssite.entity.Game;
import com.gamerssite.gamerssite.repositories.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.io.IOException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

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
            Game newGameDataJson = parseJson("https://store.steampowered.com/api/appdetails?appids="
                    + getSteamIdFromLink(url) + "&cc=ru&l=ru");
            newGameDataJson.setLink(url);
            System.out.println(newGameDataJson.toString());

            return "redirect:/";
        } catch (IOException e) {
            System.out.println("Json parse error");
            model.addAttribute("gameAddError", "Parse error");
            return "game-add";
        }
    }

    private Long getSteamIdFromLink(String link) throws IOException {
        Pattern pattern = Pattern.compile("https://store.steampowered.com/app/(\\d+)/.+/");
        Matcher matcher = pattern.matcher(link);
        if (matcher.find())
            return Long.parseLong(matcher.group(1));
        else
            throw new IOException();
    }

    private Game parseJson(String url) throws IOException {
        //ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new URL(url), Game.class);
    }
}
