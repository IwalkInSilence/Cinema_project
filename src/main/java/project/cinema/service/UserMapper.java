package project.cinema.service;

import project.cinema.model.User;
import project.cinema.model.dto.UserResponseDto;

public interface UserMapper {
    UserResponseDto parseToDto(User user);
}
