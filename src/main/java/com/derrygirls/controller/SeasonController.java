package com.derrygirls.controller;

import com.derrygirls.entity.Season;
import com.derrygirls.exception.SeasonNotFoundException;
import com.derrygirls.service.SeasonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class SeasonController {

    private static final Logger logger = LoggerFactory.getLogger(SeasonController.class);

    private SeasonService seasonService;

    @Autowired
    public void setSeasonService(SeasonService seasonService) { this.seasonService = seasonService; }

    @GetMapping("/seasons")
    public ResponseEntity<List<Season>> getAllSeasons() {
        logger.info("Listing all seasons");
        List<Season> list = seasonService.listSeasons();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/season/{id}")
    public ResponseEntity<Season> getSeason(@PathVariable("id") long id) {
        try {
            logger.info("Finding season with id {}", id);
            return new ResponseEntity<>(seasonService.findSeason(id), HttpStatus.OK);
        } catch (SeasonNotFoundException exception) {
            logger.error("Something went wrong with season id {}", id, exception);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Season Not Found");
        }
    }
}
