package project.cinema.service;

import project.cinema.model.MovieSession;
import project.cinema.model.dto.MovieSessionRequestDto;
import project.cinema.model.dto.MovieSessionResponseDto;

public interface MovieSessionMapper {
    MovieSession parseFromDto(MovieSessionRequestDto dto);

    MovieSessionResponseDto parseToDto(MovieSession movieSession);
}
