package ru.job4j.auth.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.job4j.auth.model.Person;
import ru.job4j.auth.service.PersonService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/person")
public class PersonController {
    private final PersonService simplePersonService;

    private PasswordEncoder encoder;

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
    public ResponseEntity<Person> create(@RequestBody Person person) {
        return simplePersonService.save(person)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(409).build());
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Person person) {
        ResponseEntity<Void> status = ResponseEntity.notFound().build();
        boolean isUpdated = simplePersonService.update(person);
        if (isUpdated) {
            status = ResponseEntity.ok().build();
        }
        return status;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        ResponseEntity<Void> status = ResponseEntity.notFound().build();
        Person person = new Person();
        person.setId(id);
        boolean isDeleted = simplePersonService.delete(person);
        if (isDeleted) {
            status = ResponseEntity.ok().build();
        }
        return status;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Person> signUp(@RequestBody Person person) {
        person.setPassword(encoder.encode(person.getPassword()));
        return simplePersonService.save(person)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(409).build());
    }

}