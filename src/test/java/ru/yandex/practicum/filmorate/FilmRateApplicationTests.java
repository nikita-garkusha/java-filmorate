package ru.yandex.practicum.filmorate;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.film.FilmService;
import ru.yandex.practicum.filmorate.service.user.UserService;
import ru.yandex.practicum.filmorate.storage.film.FilmStorage;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.time.LocalDate;

@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureCache
@Transactional
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@FieldDefaults(level = AccessLevel.PRIVATE)
class FilmRateApplicationTests {
    final UserStorage userStorage;
    final FilmStorage filmStorage;
    final FilmService filmService;
    final UserService userService;
    User firstUser;
    User secondUser;
    User thirdUser;
    Film firstFilm;
    Film secondFilm;
    Film thirdFilm;

    @BeforeEach
    public void beforeEach() {
        firstUser = User.builder()
                .name("MisterFirst")
                .login("First")
                .email("1@ya.ru")
                .birthday(LocalDate.of(1980, 12, 23))
                .build();

        secondUser = User.builder()
                .name("MisterSecond")
                .login("Second")
                .email("2@ya.ru")
                .birthday(LocalDate.of(1980, 12, 24))
                .build();

        thirdUser = User.builder()
                .name("MisterThird")
                .login("Third")
                .email("3@ya.ru")
                .birthday(LocalDate.of(1980, 12, 25))
                .build();

        firstFilm = Film.builder()
                .name("Breakfast at Tiffany's")
                .description("American romantic comedy film directed by Blake Edwards, written by George Axelrod," +
                        " adapted from Truman Capote's 1958 novella of the same name.")
                .releaseDate(LocalDate.of(1961, 10, 5))
                .duration(114)
                .build();


        secondFilm = Film.builder()
                .name("Avatar")
                .description("American epic science fiction film directed, written, produced, and co-edited" +
                        " by James Cameron. It is set in the mid-22nd century when humans are colonizing Pandora...")
                .releaseDate(LocalDate.of(2009, 12, 10))
                .duration(162)
                .build();


        thirdFilm = Film.builder()
                .name("One Flew Over the Cuckoo's Nest")
                .description("American psychological comedy drama film directed by Milos Forman, based on" +
                        " the 1962 novel of the same name by Ken Kesey. The film stars Jack Nicholson...")
                .releaseDate(LocalDate.of(1975, 11, 19))
                .duration(133)
                .build();
    }

}
