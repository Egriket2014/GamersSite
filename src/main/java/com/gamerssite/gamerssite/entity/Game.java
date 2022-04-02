package com.gamerssite.gamerssite.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.gamerssite.gamerssite.mappers.game.GameDeserializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "games")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonDeserialize(using = GameDeserializer.class)
public class Game {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "steam_id")
    private Long steamId;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "link")
    private String link;

    @Column(name = "image")
    private String image;

    @Column(name = "price")
    private Integer price;

    @Column(name = "min_videocard")
    private String minGraphicCard;

    @Column(name = "min_processor")
    private String minProcessor;

    @Column(name = "min_ram")
    private String minRam;

    @Column(name = "disk_memory")
    private String diskMemory;

    @ManyToMany(mappedBy = "games")
    private Set<User> users;

    @Override
    public String toString() {
        return "Title = " + title + "\n" +
                "Steam ID = " + steamId + "\n" +
                "Rating = " + rating + "\n" +
                "Link = " + link + "\n" +
                "Image = " + image + "\n" +
                "Price = " + price + "\n" +
                "MinGraphicCard = " + minGraphicCard + "\n" +
                "MinProcessor = " + minProcessor + "\n" +
                "MinRam = " + minRam + "\n" +
                "DiskMemory = " + diskMemory + "\n";
    }
}
