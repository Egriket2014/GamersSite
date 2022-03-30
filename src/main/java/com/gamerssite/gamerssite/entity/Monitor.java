package com.gamerssite.gamerssite.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "monitors")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Monitor {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "resolution")
    private String resolution;

    @Column(name = "frequency")
    private Integer frequency;

    @OneToMany(mappedBy = "memory", cascade = CascadeType.ALL)
    private Set<User> userSet;
}
