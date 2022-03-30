package com.gamerssite.gamerssite.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "graphic_cards")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Game {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "link")
    private String link;

    @Column(name = "image")
    private String image;

    @Column(name = "early_access")
    private Boolean isEarlyAccess;

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
                "Rating = " + rating + "\n" +
                "Link = " + link + "\n" +
                "Image = " + image + "\n" +
                "IsEarlyAccess = " + isEarlyAccess + "\n" +
                "Price = " + price + "\n" +
                "MinGraphicCard = " + minGraphicCard + "\n" +
                "MinProcessor = " + minProcessor + "\n" +
                "MinRam = " + minRam + "\n" +
                "DiskMemory = " + diskMemory + "\n";
    }
}
