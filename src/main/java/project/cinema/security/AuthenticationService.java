package project.cinema.security;

import project.cinema.model.User;

public interface AuthenticationService {
    User register(String email, String password);
}
