package project.football.service.impl.mappers;

import org.springframework.stereotype.Component;
import project.football.model.Game;
import project.football.model.dto.GameRequestDto;
import project.football.model.dto.GameResponseDto;
import project.football.service.GameMapper;

@Component
public class GameMapperImpl implements GameMapper {
    @Override
    public Game parseFromDto(GameRequestDto dto) {
        Game movie = new Game();
        movie.setTitle(dto.getTitle());
        movie.setDescription(dto.getDescription());
        return movie;
    }

    @Override
    public GameResponseDto parseToDto(Game movie) {
        GameResponseDto dto = new GameResponseDto();
        dto.setId(movie.getId());
        dto.setTitle(movie.getTitle());
        dto.setDescription(movie.getDescription());
        return dto;
    }
}
