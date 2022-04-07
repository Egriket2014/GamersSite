package com.gamerssite.gamerssite.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "user_picture_path")
    private String picture;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "users_games",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "game_id", referencedColumnName = "id")}
    )
    private Set<Game> games;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_friend",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "friend_id", referencedColumnName = "id")}
    )
    private Set<User> friends;

    /////////    PC     /////////

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "graphic_card_id")
    private GraphicCard graphicCard;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "memory_id")
    private Memory memory;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "processor_id")
    private Processor processor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "monitor_id")
    private Monitor monitor;
}
