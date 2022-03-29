package com.gamerssite.gamerssite.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "processors")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Processor {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "core_number")
    private Integer coreNumber;

    @Column(name = "frequency")
    private Double frequency;

    @OneToMany(mappedBy = "processor", cascade = CascadeType.ALL)
    private Set<User> userSet;
}
