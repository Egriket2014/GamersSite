package com.gamerssite.gamerssite.services.game;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gamerssite.gamerssite.dtos.GameDto;
import com.gamerssite.gamerssite.entity.Game;
import com.gamerssite.gamerssite.mappers.game.GameMapper;
import com.gamerssite.gamerssite.repositories.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final GameMapper gameMapper;

    public GameDto getGameById(Long id) {
        return gameRepository.findById(id)
                .map(gameMapper::toDto)
                .orElse(null);
    }

    public List<GameDto> getAllGame() {
        return gameMapper.toDtoList((List<Game>) gameRepository.findAll());
    }

    public long getNumberOfGames() {
        return gameRepository.count();
    }

    @Transactional
    public String addNewGameByUrlController(String url, BindingResult bindingResult, Model model) {
        if (!url.matches("https://store.steampowered.com/app/\\d+/.+/")) {
            model.addAttribute("gameAddError", "Invalid link");
            return "game-add";
        }
        if (gameRepository.findByLink(url).isPresent()) {
            model.addAttribute("gameAddError", "This game already exist in game pool");
            return "game-add";
        }
        try {
            Game newGameData = parseJson("https://store.steampowered.com/api/appdetails?appids="
                    + getSteamIdFromLink(url) + "&cc=ru&l=en");
            newGameData.setLink(url);
            gameRepository.save(newGameData);
            System.out.println(newGameData.toString());

            model.addAttribute("success", "Success");
            return "game-add";
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
