package project.cinema.service.impl.mappers;

import org.springframework.stereotype.Component;
import project.cinema.model.User;
import project.cinema.model.dto.UserResponseDto;
import project.cinema.service.UserMapper;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public UserResponseDto parseToDto(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        return dto;
    }
}
