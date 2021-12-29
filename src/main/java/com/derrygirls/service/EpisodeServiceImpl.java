package com.derrygirls.service;

import com.derrygirls.entity.Episode;
import com.derrygirls.exception.EpisodeNotFoundException;
import com.derrygirls.repository.EpisodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EpisodeServiceImpl implements EpisodeService {

    @Autowired
    private EpisodeRepository episodeRepository;

    @Override
    public List<Episode> listEpisodes() {
        return (List<Episode>) episodeRepository.findAll();
    }

    @Override
    public Episode findEpisode(long id) {
        Optional<Episode> optionalEpisode = episodeRepository.findById(id);

        if(optionalEpisode.isPresent())
            return optionalEpisode.get();
        else
            throw new EpisodeNotFoundException("Episode Not Found");
    }

    public Long count() {
        return episodeRepository.count();
    }
}
