package com.derrygirls.controller;

import com.derrygirls.dto.SeasonDTO;
import com.derrygirls.entity.Season;
import com.derrygirls.exception.SeasonNotFoundException;
import com.derrygirls.service.SeasonService;
import org.modelmapper.ModelMapper;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/derrygirls")
public class SeasonController {

    private static final Logger logger = LoggerFactory.getLogger(SeasonController.class);

    private final ModelMapper modelMapper = new ModelMapper();

    private SeasonService seasonService;

    @Autowired
    public void setSeasonService(SeasonService seasonService) { this.seasonService = seasonService; }

    @GetMapping("/seasons")
    public ResponseEntity<List<SeasonDTO>> getAllSeasons() {
        logger.info("Listing all seasons");
        List<Season> seasons = seasonService.listSeasons();
        return new ResponseEntity<>(seasons.stream()
                .map(this::transformSeasonToDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/season/{id}")
    public ResponseEntity<SeasonDTO> getSeason(@PathVariable("id") long id) {
        try {
            logger.info("Finding season with id {}", id);
            Season season = seasonService.findSeason(id);
            SeasonDTO seasonDTO = transformSeasonToDto(season);
            return new ResponseEntity<>(seasonDTO, HttpStatus.OK);
        } catch (SeasonNotFoundException exception) {
            logger.error("Something went wrong with season id {}", id, exception);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Season Not Found");
        }
    }

    private SeasonDTO transformSeasonToDto(Season season) {
        SeasonDTO seasonDto = modelMapper.map(season, SeasonDTO.class);
        seasonDto.setId(season.getId());
        seasonDto.setName(season.getName());
        return seasonDto;
    }
}
