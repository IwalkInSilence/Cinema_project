package project.cinema.service;

import project.cinema.model.Movie;
import project.cinema.model.dto.MovieRequestDto;
import project.cinema.model.dto.MovieResponseDto;

public interface MovieMapper {
    Movie parseFromDto(MovieRequestDto dto);

    MovieResponseDto parseToDto(Movie movie);
}
