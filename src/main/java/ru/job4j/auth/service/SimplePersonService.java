package ru.job4j.auth.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.job4j.auth.model.Person;
import ru.job4j.auth.repository.PersonRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;



@Service
@AllArgsConstructor
public class SimplePersonService implements PersonService {
    private final PersonRepository personRepository;

    private static final Logger LOG = LoggerFactory.getLogger(SimplePersonService.class.getName());


    @Override
    public Optional<Person> save(Person person) {
        Optional<Person> rsl = Optional.empty();
        try {
            rsl = Optional.of(personRepository.save(person));
        } catch (Exception e) {
            LOG.error("Произошла ошибка при сохранении записи в БД: " + e.getMessage());
        }
        return rsl;
    }

    @Override
    public Optional<Person> findById(Integer personId) {
        Optional<Person> rsl = Optional.empty();
        try {
            rsl = personRepository.findById(personId);
        } catch (Exception e) {
            LOG.error("Произошла ошибка при поиске пользователя по id: " + e.getMessage());
        }
        return rsl;
    }

    @Override
    public List<Person> findAll() {
        List<Person> rsl = Collections.emptyList();
        try {
            rsl = personRepository.findAll();
        } catch (Exception e) {
            LOG.error("Произошла ошибка при поиске всех пользоавтелей: " + e.getMessage());
        }
        return rsl;
    }

    @Override
    public boolean update(Person person) {
        boolean rsl = false;
        Optional<Person> currentPerson = personRepository.findById(person.getId());
        if (currentPerson.isPresent()) {
            if (!currentPerson.get().equals(person)) {
                try {
                    personRepository.save(person);
                    rsl = true;
                } catch (Exception e) {
                    LOG.error("Произошла ошибка при обновлении записи в БД: " + e.getMessage());
                }
            }
        }
        return rsl;
    }

    @Override
    public boolean delete(Person person) {
        boolean rsl = false;
        Optional<Person> currentPerson = personRepository.findById(person.getId());
        if (currentPerson.isPresent()) {
            try {
                personRepository.delete(person);
                rsl = true;
            } catch (Exception e) {
                LOG.error("Произошла ошибка при удалении записи в БД: " + e.getMessage());
            }
        }
        return rsl;
    }
}
