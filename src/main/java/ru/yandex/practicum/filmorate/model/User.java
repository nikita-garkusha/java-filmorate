package ru.yandex.practicum.filmorate.model;

import lombok.Data;
import lombok.Builder;

import javax.validation.constraints.*;
import java.time.LocalDate;


@Data
@Builder
public class User {
    private Long id;
    @NotNull(message = "Должна быть указанна эл.почта")
    @Email(message = "Некорректная почта")
    private String email;
    @NotNull(message = "Должен быть указан логин")
    @NotBlank(message = "Не пустой")
    @Pattern(regexp = "\\S+", message = "Без пробелов")
    private String login;
    private String name;
    @NotNull(message = "Должна быть указанна дата рождения")
    @Past(message = "Дата рождения не может быть позже текущей даты")
    private LocalDate birthday;


    public String getName(){
        if (name == null || name.isBlank()) {
            name = login;
        }
        return name;
    }
}
