package com.gamerssite.gamerssite.controllers;

import com.gamerssite.gamerssite.dtos.user.UserRegistrationDto;
import com.gamerssite.gamerssite.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;

    @GetMapping
    public String getRegistrationPage(Model model) {
        model.addAttribute("userRegDto", new UserRegistrationDto());
        return "registration";
    }

    @PostMapping
    public String registerNewUser(@ModelAttribute("userRegDto") @Valid UserRegistrationDto userRegistrationDto,
                                  BindingResult bindingResult,
                                  Model model) {
        return userService.registerUserController(userRegistrationDto, bindingResult, model);
    }

}
