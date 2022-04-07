package com.gamerssite.gamerssite.controllers;

import com.gamerssite.gamerssite.dtos.user.UserDto;
import com.gamerssite.gamerssite.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/profile")
    public String getProfilePage(Model model) {
        userService.getProfileController(model);
        return "user-profile";
    }

    @GetMapping("/update")
    public String getUpdateUserPage(Model model) {
        userService.getUpdateUserPageController(model);
        return "user-update";
    }

    @PostMapping("/update")
    public String getUpdateUserPage(@ModelAttribute("userDto") @Valid UserDto userDto,
                                    @RequestParam("image") MultipartFile multipartFile,
                                    BindingResult bindingResult,
                                    Model model) {
        return userService.updateUserController(userDto, multipartFile, bindingResult, model);
    }

//    @GetMapping("/displayUserPicture")
//    public void showImage(@RequestParam("id") Long userId,
//                          HttpServletResponse response) {
//        userService.displayUserPictureByUserId(userId, response);
//    }

//    @GetMapping("/displayUserPicture/{userId}")
//    public Resource showImage(@PathVariable("userId") Long userId) {
//        byte[] image = userService.getUserById(userId).getPicture();
//        if (image == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//        return new ByteArrayResource(image);
//    }
}
