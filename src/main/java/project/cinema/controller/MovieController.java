package project.cinema.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.cinema.model.dto.MovieRequestDto;
import project.cinema.model.dto.MovieResponseDto;
import project.cinema.service.MovieMapper;
import project.cinema.service.MovieService;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private MovieService movieService;
    private MovieMapper movieMapper;

    public MovieController(MovieService movieService, MovieMapper movieMapper) {
        this.movieService = movieService;
        this.movieMapper = movieMapper;
    }

    @GetMapping("/")
    public List<MovieResponseDto> getAll() {
        return movieService.getAll()
                .stream()
                .map(m -> movieMapper.parseToDto(m))
                .collect(Collectors.toList());
    }

    @PostMapping
    public void create(@RequestBody MovieRequestDto dto) {
        movieService.add(movieMapper.parseFromDto(dto));
    }
}
