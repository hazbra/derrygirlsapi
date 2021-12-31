package com.derrygirls.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="character_id")
    private long id;

    @Column(name = "character_name", nullable = false)
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "characters")
    private List<Episode> episodes;


    public Character(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Character(long id, String name, List<Episode> episodes) {
        this.id = id;
        this.name = name;
        this.episodes = episodes;
    }
}
