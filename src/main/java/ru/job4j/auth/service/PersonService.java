package ru.job4j.auth.service;

import ru.job4j.auth.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    Optional<Person> save(Person person);

    Optional<Person> findById(Integer personId);

    List<Person> findAll();

    boolean update(Person person);

    boolean delete(Person person);
}
