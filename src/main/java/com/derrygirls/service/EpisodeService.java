package com.derrygirls.service;

import com.derrygirls.entity.Episode;

import java.util.List;

public interface EpisodeService {
    List<Episode> listEpisodes();
    Episode findEpisode(long id);
    Long count();
}
