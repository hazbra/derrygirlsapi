package com.derrygirls.service;

import com.derrygirls.entity.Season;
import com.derrygirls.exception.SeasonNotFoundException;
import com.derrygirls.repository.SeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeasonServiceImpl implements SeasonService{
    
    @Autowired
    private SeasonRepository seasonRepository;

    @Override
    public List<Season> listSeasons() {
        return (List<Season>) seasonRepository.findAll();
    }

    @Override
    public Season findSeason(long id) {
        Optional<Season> optionalSeason = seasonRepository.findById(id);

        if(optionalSeason.isPresent())
            return optionalSeason.get();
        else
            throw new SeasonNotFoundException("Season Not Found");
    }

    public Long count() {
        return seasonRepository.count();
    }
}
