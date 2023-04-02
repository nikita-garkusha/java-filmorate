package ru.yandex.practicum.filmorate.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exceptions.UserNotFoundException;
import ru.yandex.practicum.filmorate.exceptions.ValidationException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {
    private UserStorage userStorage;

    @Autowired
    public UserService(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    public void addFriend(Long userId, Long friendId) {
        List<User> users = userStorage.getUsers();
        if (!users.contains(userStorage.getUserById(friendId))){
            throw new UserNotFoundException("Друг с ID=" + friendId + " не найден!");
        }
        if (userId == friendId) {
            throw new ValidationException("Нельзя добавить самого себя в друзья!");
        }
        User user = userStorage.getUserById(userId);
        user.addFriend(friendId);
    }

    public void deleteFriend(Long userId, Long friendId) {
        if (userId == friendId) {
            throw new ValidationException("Нельзя удалить самого себя из друзей!");
        }
        User user = userStorage.getUserById(userId);
        user.deleteFriend(friendId);
    }

    public Set<Long> getFriends(Long userId) {
        User user = userStorage.getUserById(userId);
        return user.getFriends();
    }

    public List<User> getCommonFriends(Long firstUserId, Long secondUserId) {
        List<User> allUsers = userStorage.getUsers();
        User firstUser = allUsers.stream()
                .filter(u -> u.getId().equals(firstUserId))
                .findFirst()
                .orElse(null);

        User secondUser = allUsers.stream()
                .filter(u -> u.getId().equals(secondUserId))
                .findFirst()
                .orElse(null);

        if (firstUser == null || secondUser == null) {
            throw new IllegalArgumentException("Пользователь не найден");
        }

        return allUsers.stream()
                .filter(u -> firstUser.getFriends().contains(u.getId()) && secondUser.getFriends().contains(u.getId()))
                .collect(Collectors.toList());
    }
}
