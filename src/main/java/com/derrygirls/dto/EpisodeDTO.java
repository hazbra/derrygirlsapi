package com.derrygirls.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class EpisodeDTO {
    long id;
    String name;
    String description;
    List<CharacterDTO> characters;
    List<QuoteDTO> quotes;
}
