package project.football.service.impl.mappers;

import org.springframework.stereotype.Component;
import project.football.model.User;
import project.football.model.dto.UserResponseDto;
import project.football.service.UserMapper;

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
