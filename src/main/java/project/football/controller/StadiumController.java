package project.football.controller;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.football.model.dto.StadiumRequestDto;
import project.football.model.dto.StadiumResponseDto;
import project.football.service.StadiumMapper;
import project.football.service.StadiumService;

@RestController
@RequestMapping("/stadiums")
public class StadiumController {
    private final StadiumService stadiumService;
    private final StadiumMapper stadiumMapper;

    public StadiumController(StadiumService stadiumService,
                             StadiumMapper stadiumMapper) {
        this.stadiumService = stadiumService;
        this.stadiumMapper = stadiumMapper;
    }

    @GetMapping
    public List<StadiumResponseDto> getAllCinemaHalls() {
        return stadiumService.getAll()
                .stream()
                .map(c -> stadiumMapper.parseToDto(c))
                .collect(Collectors.toList());
    }

    @PostMapping
    public void createCinemaHall(@RequestBody @Valid StadiumRequestDto dto) {
        stadiumService.add(stadiumMapper.parseFromDto(dto));
    }
}
