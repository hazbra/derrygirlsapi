package com.derrygirls.controller;

import com.derrygirls.entity.Episode;
import com.derrygirls.exception.EpisodeNotFoundException;
import com.derrygirls.service.EpisodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/derrygirls")
public class EpisodeController {

    private EpisodeService episodeService;

    @Autowired
    public void setEpisodeService(EpisodeService episodeService) {
        this.episodeService = episodeService;
    }

    @GetMapping("/episodes")
    public ResponseEntity<List<Episode>> getAllEpisodes() {
        List<Episode> list = episodeService.listEpisodes();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/episode/{id}")
    public ResponseEntity<Episode> getEpisode(@PathVariable("id") long id) {
        try {
            return new ResponseEntity<>(episodeService.findEpisode(id), HttpStatus.OK);
        } catch (EpisodeNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Episode Not Found");
        }
    }
}
