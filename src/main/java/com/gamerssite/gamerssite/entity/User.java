package com.gamerssite.gamerssite.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    //@Column(name = "avatar_url")
    //private String avatar;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "users_games",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    private Set<Game> games;


    /////////    PC     /////////

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "graphic_card_id")
    private GraphicCard graphicCard;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "memory_id")
    private Memory memory;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "processor_id")
    private Processor processor;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "monitor_id")
    private Monitor monitor;
}
