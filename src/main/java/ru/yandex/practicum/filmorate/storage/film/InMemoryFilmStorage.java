package ru.yandex.practicum.filmorate.storage.film;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exceptions.FilmNotFoundException;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("inMemoryFilmStorage")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InMemoryFilmStorage implements FilmStorage {
    final Map<Long, Film> films;
    Long currentId;

    public InMemoryFilmStorage() {
        currentId = 0L;
        films = new HashMap<>();
    }

    @Override
    public List<Film> getFilms() {
        return new ArrayList<>(films.values());
    }

    @Override
    public Film create(Film film) {
        if (isValidFilm(film)) {
            film.setId(++currentId);
            films.put(film.getId(), film);
        }
        return film;
    }

    @Override
    public Film update(Film film) {
        if (film.getId() == null) {
            throw new ValidationException("Передан пустой аргумент!");
        }
        if (!films.containsKey(film.getId())) {
            throw new FilmNotFoundException("Фильм с ID=" + film.getId() + " не найден!");
        }
        if (isValidFilm(film)) {
            films.put(film.getId(), film);
        }
        return film;
    }

    @Override
    public Film getFilmById(Long filmId) {
        if (!films.containsKey(filmId)) {
            throw new FilmNotFoundException("Фильм с ID=" + filmId + " не найден!");
        }
        return films.get(filmId);
    }

    @Override
    public Film delete(Long filmId) {
        if (filmId == null) {
            throw new ValidationException("Передан пустой аргумент!");
        }
        if (!films.containsKey(filmId)) {
            throw new FilmNotFoundException("Фильм с ID=" + filmId + " не найден!");
        }
        return films.remove(filmId);
    }

    private boolean isValidFilm(Film film) {
        System.out.println("ВХОД В УСЛОВИЕ");
        if (film.getName().isEmpty()) {
            throw new ValidationException("Название фильма не должно быть пустым!");
        }
        if ((film.getDescription().length()) > 200 || (film.getDescription().isEmpty())) {
            throw new ValidationException(String.format("Описание фильма больше 200 символов или пустое: %s", film.getDescription().length()));
        }
        if (film.getReleaseDate().isBefore(LocalDate.of(1895, 12, 28))) {
            throw new ValidationException(String.format("Некорректная дата релиза фильма: %s", film.getReleaseDate()));
        }
        if (film.getDuration() <= 0) {
            throw new ValidationException(String.format("Продолжительность должна быть положительной: %s", film.getDuration()));
        }
        return true;
    }
}
