package ru.job4j.auth.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.auth.model.Person;
import ru.job4j.auth.service.PersonService;

import java.sql.SQLException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/person")
public class PersonController {
    private final PersonService simplePersonService;

    @GetMapping("/")
    public List<Person> findAll() {
        return simplePersonService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> findById(@PathVariable int id) {
        return simplePersonService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public ResponseEntity<Person> create(@RequestBody Person person) throws SQLException {
        return simplePersonService.save(person)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new SQLException("An error occurred while saving data"));
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Person person) throws SQLException {
        var isUpdated = simplePersonService.update(person);
        if (!isUpdated) {
            throw new SQLException(String.format("An error occurred while updating person with id=%s", person.getId()));
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) throws SQLException {
        Person person = new Person();
        person.setId(id);
        var isDeleted = simplePersonService.delete(person);
        if (!isDeleted) {
            throw new SQLException(String.format("An error occurred while deleting person with id=%s", id));
        }
        return ResponseEntity.ok().build();
    }
}