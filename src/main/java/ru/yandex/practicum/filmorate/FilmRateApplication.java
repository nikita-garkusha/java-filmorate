package ru.yandex.practicum.filmorate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class FilmRateApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(FilmRateApplication.class, args);
		try {
			TimeUnit.SECONDS.sleep(10);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		ctx.close();
	}

}
