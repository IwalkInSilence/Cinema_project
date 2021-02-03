package project.cinema.security;

import project.cinema.exception.AuthenticationException;
import project.cinema.model.User;

public interface AuthenticationService {
    User login(String email, String password) throws AuthenticationException;

    User register(String email, String password);
}
