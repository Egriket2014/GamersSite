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
    private String linkOnSteam;

    @Column(name = "early_access")
    private Boolean isEarlyAccess;

    @Column(name = "last_patch")
    private Date lastPatch;

    @Column(name = "price")
    private Integer price;

    @ManyToMany(mappedBy = "games")
    private Set<User> users;
}
