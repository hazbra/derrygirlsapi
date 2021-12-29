package com.derrygirls.service;

import com.derrygirls.entity.Season;

import java.util.List;

public interface SeasonService {
    List<Season> listSeasons();
    Season findSeason(long id);
    Long count();
}
