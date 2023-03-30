package ru.yandex.practicum.filmorate.model;

import lombok.Data;
import ru.yandex.practicum.filmorate.exceptions.annotation.AfterCinemaBirthday;

import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;


@Data
public class Film {
    private Long id;
    @NotNull(message = "Имя не может быть пустым")
    @NotBlank(message = "Имя не может быть пустым")
    private String name;

    @NotNull(message = "Описание не может быть пустым")
    @Size (max = 200, message = "Не более 200 символов")
    private String description;

    @NotNull(message = "Должна быть дата релиза")
    @AfterCinemaBirthday
    private LocalDate releaseDate;
    @NotNull(message = "продолжительность должна быть")
    @Positive(message = "Продолжительность фильма должна быть положительной")
    private Integer duration;
}
