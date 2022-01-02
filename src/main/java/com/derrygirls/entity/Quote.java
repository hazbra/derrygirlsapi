package com.derrygirls.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="quote_id")
    private long id;

    @Column(length = 2000)
    private String description;

    @JoinColumn(name = "character_id", nullable = false)
    private long characterId;

    @JoinColumn(name = "episode_id", nullable = false)
    private long episodeId;



    public Quote(long id, String description, long characterId, long episodeId) {
        this.id = id;
        this.description = description;
        this.characterId = characterId;
        this.episodeId = episodeId;
    }
}
