package com.example.cohan.domain.school.port.input;

import com.example.cohan.domain.school.entity.PersonEntity;
import com.example.cohan.domain.school.enums.PersonType;
import com.example.cohan.domain.school.model.Person;

import java.util.Optional;

public interface PersonPort {
    Optional<Long> save(Person person);
    Boolean existsByDni(String dni, PersonType type);
}
