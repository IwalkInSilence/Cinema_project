package project.cinema.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.cinema.model.dto.CinemaHallRequestDto;
import project.cinema.model.dto.CinemaHallResponseDto;
import project.cinema.service.CinemaHallMapper;
import project.cinema.service.CinemaHallService;

@RestController
@RequestMapping("/cinema-halls")
public class CinemaHallController {
    private CinemaHallService cinemaHallService;
    private CinemaHallMapper cinemaHallMapper;

    public CinemaHallController(CinemaHallService cinemaHallService,
                                CinemaHallMapper cinemaHallMapper) {
        this.cinemaHallService = cinemaHallService;
        this.cinemaHallMapper = cinemaHallMapper;
    }

    @GetMapping
    public List<CinemaHallResponseDto> getAllCinemaHalls() {
        return cinemaHallService.getAll()
                .stream()
                .map(c -> cinemaHallMapper.parseToDto(c))
                .collect(Collectors.toList());
    }

    @PostMapping
    public void createCinemaHall(@RequestBody CinemaHallRequestDto dto) {
        cinemaHallService.add(cinemaHallMapper.parseFromDto(dto));
    }
}
