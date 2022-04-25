package com.derrygirls.controller;

import com.derrygirls.dto.CharacterDTO;
import com.derrygirls.dto.EpisodeDTO;
import com.derrygirls.dto.QuoteDTO;
import com.derrygirls.entity.Episode;
import com.derrygirls.entity.Character;;
import com.derrygirls.entity.Quote;
import com.derrygirls.exception.EpisodeNotFoundException;
import com.derrygirls.service.EpisodeService;
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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/derrygirls")
public class EpisodeController {

    private static final Logger logger = LoggerFactory.getLogger(EpisodeController.class);

    private static final ModelMapper modelMapper = new ModelMapper();

    private EpisodeService episodeService;


    @Autowired
    public void setEpisodeService(EpisodeService episodeService) {
        this.episodeService = episodeService;
    }

    @GetMapping("/episodes")
    public ResponseEntity<List<EpisodeDTO>> getAllEpisodes() {
        logger.info("Listing all episodes");
        List<Episode> episodes = episodeService.listEpisodes();
        return new ResponseEntity<>(episodes.stream()
                .map(this::transformEpisodeToDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/episode/{id}")
    public ResponseEntity<EpisodeDTO> getEpisode(@PathVariable("id") long id) {
        try {
            logger.info("Finding episode with id {}", id);
            Episode episode = episodeService.findEpisode(id);
            EpisodeDTO episodeDTO = transformEpisodeToDto(episode);
            return new ResponseEntity<>(episodeDTO, HttpStatus.OK);
        } catch (EpisodeNotFoundException exception) {
            logger.error("Something went wrong with episode id {}", id, exception);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Episode Not Found");
        }
    }

    public EpisodeDTO transformEpisodeToDto(Episode episode) {
        EpisodeDTO episodeDto = modelMapper.map(episode, EpisodeDTO.class);
        episodeDto.setId(episode.getId());
        episodeDto.setName(episode.getName());
        episodeDto.setDescription(episode.getDescription());
        episodeDto.setCharacters(transformCharacterListToDtoList(episode.getCharacters()));
        episodeDto.setQuotes(transformQuoteListToDtoList(episode.getQuotes()));
        return episodeDto;
    }

    public static List<CharacterDTO> transformCharacterListToDtoList(List<Character> characters) {
        List<CharacterDTO> characterDtos = new ArrayList<>();
        for(Character character: characters){
            CharacterDTO characterDto = modelMapper.map(character, CharacterDTO.class);
            characterDto.setId(character.getId());
            characterDto.setName(character.getName());
            characterDtos.add(characterDto);
        }
        return characterDtos;
    }

    public static List<QuoteDTO> transformQuoteListToDtoList(List<Quote> quotes) {
        List<QuoteDTO> quoteDTOS = new ArrayList<>();
        for(Quote quote: quotes){
            QuoteDTO quoteDto = modelMapper.map(quote, QuoteDTO.class);
            quoteDto.setId(quote.getId());
            quoteDto.setDescription(quote.getDescription());
            quoteDTOS.add(quoteDto);
        }
        return quoteDTOS;
    }
}
