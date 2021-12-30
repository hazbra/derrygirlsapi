package com.derrygirls.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(mappedBy = "seasonId")
    private List<Episode> episodes;

    public Season(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Season(long id, String name, List<Episode> episodes) {
        this.id = id;
        this.name = name;
        this.episodes = episodes;
    }
}
