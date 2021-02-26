package project.football.service.impl.mappers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;
import project.football.model.Game;
import project.football.model.GameSession;
import project.football.model.Stadium;
import project.football.model.dto.GameSessionRequestDto;
import project.football.model.dto.GameSessionResponseDto;
import project.football.service.GameService;
import project.football.service.GameSessionMapper;
import project.football.service.StadiumService;

@Component
public class GameSessionMapperImpl implements GameSessionMapper {
    private final GameService gameService;
    private final StadiumService stadiumService;

    public GameSessionMapperImpl(GameService gameService, StadiumService stadiumService) {
        this.gameService = gameService;
        this.stadiumService = stadiumService;
    }

    @Override
    public GameSession parseFromDto(GameSessionRequestDto dto) {
        Game movie = gameService.get(dto.getGameId());
        Stadium stadium = stadiumService.get(dto.getStadiumId());
        GameSession gameSession = new GameSession();
        gameSession.setMovie(movie);
        gameSession.setCinemaHall(stadium);
        LocalDateTime dateTime = LocalDateTime.parse(dto.getLocalTime(),
                DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        gameSession.setShowTime(dateTime);
        return gameSession;
    }

    @Override
    public GameSessionResponseDto parseToDto(GameSession gameSession) {
        GameSessionResponseDto dto = new GameSessionResponseDto();
        dto.setId(gameSession.getId());
        dto.setGameId(gameSession.getMovie().getId());
        dto.setTitle(gameSession.getMovie().getTitle());
        dto.setGameSessionId(gameSession.getCinemaHall().getId());
        dto.setLocalTime(gameSession.getShowTime().toString());
        return dto;
    }
}
