package com.gamerssite.gamerssite.controllers;

import com.gamerssite.gamerssite.dtos.game.UrlDto;
import com.gamerssite.gamerssite.services.game.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/game")
public class GameController {

    private final GameService gameService;

    @RequestMapping("/add")
    public String getNewGameByUrlPage(Model model) {
        model.addAttribute("newGameUrl", new UrlDto());
        return "game-add";
    }

    @PostMapping("/add")
    public String addNewGameByUrl(@ModelAttribute("newGameUrl") @Valid UrlDto newGameUrl,
                                  BindingResult bindingResult,
                                  Model model) {
        return gameService.addNewGameByUrlController(newGameUrl.getUrl(), bindingResult, model);
    }

    @GetMapping("/{id}")
    public String showGameInfo(@PathVariable("id") Long id, Model model) {
        model.addAttribute("gameDto", gameService.getGameById(id));
        return "game-id";
    }
}
