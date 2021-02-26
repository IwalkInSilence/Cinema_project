package project.football.dao;

import java.util.Optional;
import project.football.model.User;

public interface UserDao {
    User add(User user);

    Optional<User> findByEmail(String email);

    Optional<User> getById(Long id);
}
