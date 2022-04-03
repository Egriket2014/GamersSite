package com.gamerssite.gamerssite.dtos.game;

import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class UrlDto {

    @Pattern(regexp = "https://store.steampowered.com/app/\\d+/.+/", message = "Invalid link")
    private String url;
}
