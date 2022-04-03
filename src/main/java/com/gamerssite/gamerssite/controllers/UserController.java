package com.gamerssite.gamerssite.controllers;

import com.gamerssite.gamerssite.dtos.user.UserRegistrationDto;
import com.gamerssite.gamerssite.services.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

}
