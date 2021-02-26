package project.football.service;

import project.football.model.User;
import project.football.model.dto.UserResponseDto;

public interface UserMapper {
    UserResponseDto parseToDto(User user);
}
