package project.cinema.service.impl.mappers;

import org.springframework.stereotype.Component;
import project.cinema.model.CinemaHall;
import project.cinema.model.dto.CinemaHallRequestDto;
import project.cinema.model.dto.CinemaHallResponseDto;
import project.cinema.service.CinemaHallMapper;

@Component
public class CinemaHallMapperImpl implements CinemaHallMapper {
    @Override
    public CinemaHall parseFromDto(CinemaHallRequestDto dto) {
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(dto.getCapacity());
        cinemaHall.setDescription(dto.getDescription());
        return cinemaHall;
    }

    @Override
    public CinemaHallResponseDto parseToDto(CinemaHall cinemaHall) {
        CinemaHallResponseDto dto = new CinemaHallResponseDto();
        dto.setId(cinemaHall.getId());
        dto.setDescription(cinemaHall.getDescription());
        dto.setCapacity(cinemaHall.getCapacity());
        return dto;
    }
}
