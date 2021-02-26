package project.football.controller;

import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.football.model.GameSession;
import project.football.model.dto.GameSessionRequestDto;
import project.football.model.dto.GameSessionResponseDto;
import project.football.service.GameSessionMapper;
import project.football.service.GameSessionService;

@RestController
@RequestMapping("/game-sessions")
public class GameSessionController {
    private final GameSessionService gameSessionService;
    private final GameSessionMapper gameSessionMapper;

    public GameSessionController(GameSessionService gameSessionService,
                                 GameSessionMapper gameSessionMapper) {
        this.gameSessionService = gameSessionService;
        this.gameSessionMapper = gameSessionMapper;
    }

    @GetMapping("/available")
    public List<GameSessionResponseDto> getAll(
            @RequestParam Long movieId,
            @RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate date) {
        return gameSessionService.findAvailableSessions(movieId, date)
                .stream()
                .map(m -> gameSessionMapper.parseToDto(m))
                .collect(Collectors.toList());
    }

    @PostMapping
    public void create(@RequestBody @Valid GameSessionRequestDto dto) {
        gameSessionService.add(gameSessionMapper.parseFromDto(dto));
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody @Valid GameSessionRequestDto dto) {
        GameSession gameSession = gameSessionMapper.parseFromDto(dto);
        gameSession.setId(id);
        gameSessionService.update(gameSession);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        gameSessionService.delete(id);
    }
}
