package com.derrygirls.controller;

import com.derrygirls.dto.CharacterDTO;
import com.derrygirls.entity.Character;
import com.derrygirls.exception.CharacterNotFoundException;
import com.derrygirls.service.CharacterService;
import com.derrygirls.utils.DerryGirlsUtils;
import com.derrygirls.utils.DerryGirlsUtils.*;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
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
public class CharacterController {

    private static final Logger logger = LoggerFactory.getLogger(CharacterController.class);

    private static final ModelMapper modelMapper = new ModelMapper();

    private CharacterService characterService;

    @Value("${does.not.exist}")
    public String defaultMessage;

    @Autowired
    public void setCharacterService(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping("/characters")
    public ResponseEntity<List<CharacterDTO>> getAllCharacters() {
        logger.info("Listing all characters");
        List<Character> characters = characterService.listCharacters();
        return new ResponseEntity<>(characters.stream()
                .map(this::transformToDto)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/character/{id}")
    public ResponseEntity<Character> getCharacter(@PathVariable("id") long id) {
        try {
            logger.info("Finding character with id {}", id);
            return new ResponseEntity<>(characterService.findCharacter(id), HttpStatus.OK);
        } catch (CharacterNotFoundException exception) {
            logger.error("Something went wrong with character id {}", id, exception);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    DerryGirlsUtils.addExceptionMessage(id, defaultMessage, "Character"));
        }
    }

    private CharacterDTO transformToDto(Character character) {
        CharacterDTO characterDto = modelMapper.map(character, CharacterDTO.class);
        characterDto.setId(character.getId());
        characterDto.setName(character.getName());
        return characterDto;
    }
}
