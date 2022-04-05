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
    private String name;

    @OneToMany(mappedBy = "memory", cascade = CascadeType.ALL)
    private Set<User> userSet;

    @Override
    public String toString() {
        return name;
    }
}
