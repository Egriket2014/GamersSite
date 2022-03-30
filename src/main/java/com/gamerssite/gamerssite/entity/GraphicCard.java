package com.gamerssite.gamerssite.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "graphic_cards")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GraphicCard {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "memory")
    private Integer memory;

    @OneToMany(mappedBy = "graphicCard", cascade = CascadeType.ALL)
    private Set<User> userSet;
}
