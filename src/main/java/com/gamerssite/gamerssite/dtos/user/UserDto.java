package com.gamerssite.gamerssite.dtos.user;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;

@Data
@Builder
public class UserDto {

    private long id;
    private String name;
    private String picture;

    @Email(message = "Email should be valid")
    private String email;

    private String graphicCard;
    private String memory;
    private String processor;
    private String monitor;
}
