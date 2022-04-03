package com.gamerssite.gamerssite.dtos.user;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class UserRegistrationDto {

    @NotEmpty(message = "Login should not be empty")
    @Size(min = 2, max = 50)
    private String login;

    @NotEmpty(message = "Password should not be empty")
    private String password;

    @NotEmpty(message = "Confirm password should not be empty")
    private String confirmPassword;

    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email should be valid")
    private String email;


    public boolean passwordsMatch() {
        return password.equals(confirmPassword);
    }
}
