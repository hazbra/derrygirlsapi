package com.derrygirls.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Episode {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="episode_id")
    private long id;

    @Column(name = "episode_name", nullable = false)
    private String name;

    @Column(length = 2000)
    private String description;

    @JoinColumn(name = "season_id", nullable = false)
    private long seasonId;

    public Episode(long id, String name, String description, long season_id) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.seasonId = season_id;
    }
}
