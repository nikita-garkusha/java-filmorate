package ru.yandex.practicum.filmorate.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    private UserStorage userStorage;

    @GetMapping
    public List<User> getUsers(){
        return userStorage.getUsers();
    }

    @ResponseBody
    @PostMapping
    public User create(@Valid @RequestBody User user) {
        user = userStorage.create(user);
        return user;
    }

    @ResponseBody
    @PutMapping
    public User update(@Valid @RequestBody User user) {
        user = userStorage.update(user);
        return user;
    }

}
