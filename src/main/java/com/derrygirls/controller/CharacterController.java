package com.derrygirls.controller;

import com.derrygirls.entity.Character;
import com.derrygirls.exception.CharacterNotFoundException;
import com.derrygirls.service.CharacterService;
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
public class CharacterController {

    private static final Logger logger = LoggerFactory.getLogger(CharacterController.class);

    private CharacterService characterService;

    @Autowired
    public void setCharacterService(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping("/characters")
    public ResponseEntity<List<Character>> getAllCharacters() {
        logger.info("Listing all characters");
        List<Character> list = characterService.listCharacters();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/character/{id}")
    public ResponseEntity<Character> getCharacter(@PathVariable("id") long id) {
        try {
            logger.info("Finding character with id {}", id);
            return new ResponseEntity<>(characterService.findCharacter(id), HttpStatus.OK);
        } catch (CharacterNotFoundException exception) {
            logger.error("Something went wrong with character id {}", id, exception);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Character Not Found");
        }
    }
}
