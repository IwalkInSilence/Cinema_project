package project.cinema.service;

import project.cinema.model.CinemaHall;
import project.cinema.model.dto.CinemaHallRequestDto;
import project.cinema.model.dto.CinemaHallResponseDto;

public interface CinemaHallMapper {
    CinemaHall parseFromDto(CinemaHallRequestDto dto);

    CinemaHallResponseDto parseToDto(CinemaHall cinemaHall);
}
