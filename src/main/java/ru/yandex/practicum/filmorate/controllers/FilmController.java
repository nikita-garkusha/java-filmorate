package ru.yandex.practicum.filmorate.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.film.FilmService;
import ru.yandex.practicum.filmorate.storage.film.FilmStorage;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/films")
@Slf4j
public class FilmController {
    private FilmStorage filmStorage;
    private FilmService filmService;


    public FilmController(FilmStorage filmStorage, FilmService filmService) {
        this.filmStorage = filmStorage;
        this.filmService = filmService;
    }

    @GetMapping
    public List<Film> getFilms() {
        return filmStorage.getFilms();
    }


    @ResponseBody
    @PostMapping
    public Film create(@Valid @RequestBody Film film) {
        log.info("Получен POST-запрос к эндпоинту: '/films' на добавление фильма");
        film = filmStorage.create(film);
        return film;
    }

    @ResponseBody
    @PutMapping
    public Film update(@Valid @RequestBody Film film) {
        log.info("Получен PUT-запрос к эндпоинту: '/films' на обновление фильма с ID={}", film.getId());
        film = filmStorage.update(film);
        return film;
    }
}