package com.gamerssite.gamerssite.controllers;

import com.gamerssite.gamerssite.services.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @RequestMapping("game-add")
    public String addNewGameByUrl(Model model) {
        return gameService.addNewGameByUrlController("https://store.steampowered.com/app/359320/Elite_Dangerous/", model);
    }
}
