package com.gamerssite.gamerssite.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "memories")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Memory {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "capacity")
    private Integer capacity;

    @OneToMany(mappedBy = "memory", cascade = CascadeType.ALL)
    private Set<User> userSet;
}
