package ru.job4j.auth.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Data
@EqualsAndHashCode
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "Id must be not null")
    private Integer id;

    @NotBlank(message = "Login must be not empty")
    private String login;

    @NotBlank(message = "Password must be not empty")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z]).+$",
            message = "Password must contain at least one lowercase and at least one uppercase letter.")
    private String password;
}