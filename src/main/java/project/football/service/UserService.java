package project.football.service;

import java.util.Optional;
import project.football.model.User;

public interface UserService {
    User add(User user);

    Optional<User> findByEmail(String email);

    User getById(Long id);
}
