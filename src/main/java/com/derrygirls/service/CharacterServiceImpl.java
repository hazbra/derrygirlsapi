package com.derrygirls.service;

import com.derrygirls.entity.Character;
import com.derrygirls.exception.CharacterNotFoundException;
import com.derrygirls.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CharacterServiceImpl implements CharacterService {

    @Autowired
    private CharacterRepository characterRepository;

    @Override
    public List<Character> listCharacters() {
        return (List<Character>) characterRepository.findAll();
    }

    @Override
    public Character findCharacter(long id) {
        Optional<Character> optionalCharacter = characterRepository.findById(id);

        if(optionalCharacter.isPresent())
            return optionalCharacter.get();
        else
            throw new CharacterNotFoundException("Character Not Found");
    }

    public Long count() {
        return characterRepository.count();
    }
}
