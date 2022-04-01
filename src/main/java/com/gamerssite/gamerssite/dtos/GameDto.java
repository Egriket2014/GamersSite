package com.gamerssite.gamerssite.dtos;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;

@Data
@Builder
public class GameDto {
    private String title;
    private Double rating;
    private String link;
    private String image;
    private Integer price;
    private String minGraphicCard;
    private String minProcessor;
    private String minRam;
    private String diskMemory;
}
