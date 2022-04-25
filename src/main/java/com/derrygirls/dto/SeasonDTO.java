package com.derrygirls.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SeasonDTO {
    long id;
    String name;
    List<EpisodeDTO> episodes;
}
