package com.derrygirls.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Season {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="season_id")
    private long id;

    @Column(name = "season_name", nullable = false)
    private String name;

    public Season(long id, String name) {
        this.id = id;
        this.name = name;
    }
}
