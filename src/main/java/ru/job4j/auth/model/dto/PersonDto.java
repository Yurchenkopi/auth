package ru.job4j.auth.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class PersonDto {
    @EqualsAndHashCode.Include
    private int id;

    private String password;
}
