package com.derrygirls.repository;

import com.derrygirls.entity.Character;
import org.springframework.data.repository.CrudRepository;

public interface CharacterRepository extends CrudRepository<Character, Long> {
}
