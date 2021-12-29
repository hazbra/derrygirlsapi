package com.derrygirls.repository;

import com.derrygirls.entity.Episode;
import org.springframework.data.repository.CrudRepository;

public interface EpisodeRepository extends CrudRepository<Episode, Long> {
}
