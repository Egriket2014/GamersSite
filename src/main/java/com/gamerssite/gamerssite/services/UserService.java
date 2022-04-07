package com.gamerssite.gamerssite.services;

import com.gamerssite.gamerssite.dtos.user.UserDto;
import com.gamerssite.gamerssite.dtos.user.UserRegistrationDto;
import com.gamerssite.gamerssite.entity.User;
import com.gamerssite.gamerssite.mappers.user.UserMapper;
import com.gamerssite.gamerssite.repositories.FileSystemRepository;
import com.gamerssite.gamerssite.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder bCryptPasswordEncoder;
    private final FileSystemRepository fileSystemRepository;

    public UserDto getUserDtoByLogin(String login) {
        return userMapper.toDto(userRepository.findByLogin(login).orElse(null));
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Transactional
    public void registerUser(UserRegistrationDto dto) {
        try {
            User newUser = User.builder()
                    .name(dto.getLogin())
                    .login(dto.getLogin())
                    .email(dto.getEmail())
                    .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                    .picture("defaultAvatar.jpg")
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

    public void getProfileController(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = getUserDtoByLogin(authentication.getName());
        model.addAttribute("userDto", userDto);
    }

    public void getUpdateUserPageController(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = getUserDtoByLogin(authentication.getName());
        model.addAttribute("userDto", userDto);
    }

    @Transactional
    public String updateUserController(UserDto userDto, MultipartFile multipartFile,
                                       BindingResult bindingResult, Model model) {
        try {
            if (bindingResult.hasErrors()) {
                return "user-update";
            }
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentUserEmail = getUserDtoByLogin(authentication.getName()).getEmail();
            String newEmail = userDto.getEmail();
            if (!currentUserEmail.equals(newEmail) && userRepository.findByEmail(newEmail).isPresent()) {
                model.addAttribute("userDto", userDto);
                model.addAttribute("updateError", "User with this email is already registered");
                return "user-update";
            }

            updateUser(userDto, multipartFile);
            return "redirect:/user/profile";
        } catch (UsernameNotFoundException | IOException e) {
            System.out.println("User update error " + e);
            return "user-update";
        }
    }

    @Transactional
    public void updateUser(UserDto userDto, MultipartFile multipartFile) throws UsernameNotFoundException, IOException {
        User user = userRepository.findById(userDto.getId()).orElseThrow(
                () -> new UsernameNotFoundException("User with id = " + userDto.getId() + "not found"));

        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());

        if (multipartFile != null) {
            if (!user.getPicture().equals("defaultAvatar.jpg"))
                fileSystemRepository.delete(user.getPicture());
            user.setPicture(fileSystemRepository.save(multipartFile));
        }

        userRepository.save(user);
    }

//    public void displayUserPictureByUserId(Long id, HttpServletResponse response) {
//        try {
//            User user = userRepository.findById(id).orElseThrow(
//                    () -> new UsernameNotFoundException("User with id = " + id + "not found"));
//
//            response.setContentType("image/jpeg");
//            response.getOutputStream().write(user.getPicture());
//            response.getOutputStream().close();
//        } catch (IOException e) {
//            System.out.println("Display UserPicture error");
//        }
//    }
}
