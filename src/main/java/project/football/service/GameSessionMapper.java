package project.football.service;

import project.football.model.GameSession;
import project.football.model.dto.GameSessionRequestDto;
import project.football.model.dto.GameSessionResponseDto;

public interface GameSessionMapper {
    GameSession parseFromDto(GameSessionRequestDto dto);

    GameSessionResponseDto parseToDto(GameSession gameSession);
}
