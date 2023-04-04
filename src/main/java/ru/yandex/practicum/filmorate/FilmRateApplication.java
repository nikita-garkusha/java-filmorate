package ru.yandex.practicum.filmorate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class FilmRateApplication {

    public static void main(String[] args) {
        SpringApplication.run(FilmRateApplication.class, args);
        //просто комментарий для проверки тестов и гита
    }
}
