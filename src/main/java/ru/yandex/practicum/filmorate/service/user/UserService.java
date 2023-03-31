package ru.yandex.practicum.filmorate.service.user;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

@Service
public class UserService {
    private UserStorage userStorage;
//    @Autowired
//    public UserService(@Qualifier("UserStorage") UserStorage userStorage) {
//        this.userStorage = userStorage;
//    }

}
