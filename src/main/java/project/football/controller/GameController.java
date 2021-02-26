package project.football.controller;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.football.model.dto.GameRequestDto;
import project.football.model.dto.GameResponseDto;
import project.football.service.GameMapper;
import project.football.service.GameService;

@RestController
@RequestMapping("/games")
public class GameController {
    private final GameService gameService;
    private final GameMapper gameMapper;

    public GameController(GameService gameService, GameMapper gameMapper) {
        this.gameService = gameService;
        this.gameMapper = gameMapper;
    }

    @GetMapping
    public List<GameResponseDto> getAll() {
        return gameService.getAll()
                .stream()
                .map(m -> gameMapper.parseToDto(m))
                .collect(Collectors.toList());
    }

    @PostMapping
    public void create(@RequestBody @Valid GameRequestDto dto) {
        gameService.add(gameMapper.parseFromDto(dto));
    }
}
