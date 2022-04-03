package com.gamerssite.gamerssite.services.user;

import com.gamerssite.gamerssite.dtos.user.UserRegistrationDto;
import com.gamerssite.gamerssite.entity.*;
import com.gamerssite.gamerssite.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public void registerUser(UserRegistrationDto dto) {
        try {
            User newUser = User.builder()
                    .name(dto.getLogin())
                    .login(dto.getLogin())
                    .email(dto.getEmail())
                    .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                    .build();

            userRepository.save(newUser);
        } catch (Exception e) {
            System.out.println("User registration error");
        }
    }

    @Transactional
    public String registerUserController(UserRegistrationDto dto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        if (userRepository.findByLogin(dto.getLogin()).isPresent()) {
            model.addAttribute("userErrorLogin", "User with this login is already registered");
            return "registration";
        }
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            model.addAttribute("userErrorEmail", "User with this email address is already registered");
            return "registration";
        }
        if (!dto.passwordsMatch()) {
            model.addAttribute("userErrorPassword", "Passwords does not match");
            return "registration";
        }

        registerUser(dto);
        return "redirect:/";
    }
}
