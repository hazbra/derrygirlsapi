package com.derrygirls.service;

import com.derrygirls.entity.Character;

import java.util.List;

public interface CharacterService {
    List<Character> listCharacters();
    Character findCharacter(long id);
    Long count();
}
