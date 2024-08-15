package ru.job4j.auth.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.job4j.auth.repository.PersonRepository;

import static java.util.Collections.emptyList;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final PersonRepository personRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var person = personRepository.findPersonByLogin(username);
        if (person.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }
        return new User(person.get().getLogin(), person.get().getPassword(), emptyList());
    }
}
