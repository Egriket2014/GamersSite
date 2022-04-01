package com.gamerssite.gamerssite.controllers;

import com.gamerssite.gamerssite.dtos.UrlDto;
import com.gamerssite.gamerssite.services.game.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @RequestMapping("/game-add")
    public String getNewGameByUrlPage(Model model) {
        model.addAttribute("newGameUrl", new UrlDto());
        return "game-add";
    }

    @PostMapping("/game-add")
    public String addNewGameByUrl(@ModelAttribute UrlDto newGameUrl, Model model) {
        return gameService.addNewGameByUrlController(newGameUrl.url, model);
    }
}
