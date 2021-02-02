package project.cinema.service;

import java.util.Optional;
import project.cinema.model.User;

public interface UserService {
    User add(User user);

    Optional<User> findByEmail(String email);
}
