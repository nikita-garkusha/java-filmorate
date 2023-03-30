package ru.yandex.practicum.filmorate.controllers;


import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.FilmStorage;

import java.util.List;

@RestController
public class FilmController {
    private FilmStorage filmStorage;

    @GetMapping
    public List<Film> getFilms(){
        return filmStorage.getFilms();
    }

    @ResponseBody
    @PostMapping
    public Film create(@RequestBody Film film){
        film = filmStorage.create(film);
        return film;
    }

    @ResponseBody
    @PutMapping
    public Film update(@RequestBody Film film){
        film = filmStorage.update(film);
        return film;
    }
}
