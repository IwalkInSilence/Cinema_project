package project.football.security;

import project.football.model.User;

public interface AuthenticationService {
    User register(String email, String password);
}
