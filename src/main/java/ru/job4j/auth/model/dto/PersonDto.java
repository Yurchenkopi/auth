package ru.job4j.auth.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@EqualsAndHashCode
public class PersonDto {
    @EqualsAndHashCode.Include
    @NotNull(message = "Id must be not null")
    private Integer id;

    @NotBlank(message = "Password must be not empty")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z]).+$",
            message = "Password must contain at least one lowercase and at least one uppercase letter.")
    private String password;
}
