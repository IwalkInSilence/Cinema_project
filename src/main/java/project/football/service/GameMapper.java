package project.football.service;

import project.football.model.Game;
import project.football.model.dto.GameRequestDto;
import project.football.model.dto.GameResponseDto;

public interface GameMapper {
    Game parseFromDto(GameRequestDto dto);

    GameResponseDto parseToDto(Game movie);
}
