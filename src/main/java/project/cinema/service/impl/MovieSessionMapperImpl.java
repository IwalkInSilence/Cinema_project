package project.cinema.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;
import project.cinema.model.CinemaHall;
import project.cinema.model.Movie;
import project.cinema.model.MovieSession;
import project.cinema.model.dto.MovieSessionRequestDto;
import project.cinema.model.dto.MovieSessionResponseDto;
import project.cinema.service.CinemaHallService;
import project.cinema.service.MovieService;
import project.cinema.service.MovieSessionMapper;

@Component
public class MovieSessionMapperImpl implements MovieSessionMapper {
    private MovieService movieService;
    private CinemaHallService cinemaHallService;

    public MovieSessionMapperImpl(MovieService movieService, CinemaHallService cinemaHallService) {
        this.movieService = movieService;
        this.cinemaHallService = cinemaHallService;
    }

    @Override
    public MovieSession parseFromDto(MovieSessionRequestDto dto) {
        Movie movie = movieService.get(dto.getMovieId());
        CinemaHall cinemaHall = cinemaHallService.get(dto.getCinemaHallId());
        MovieSession movieSession = new MovieSession();
        movieSession.setMovie(movie);
        movieSession.setCinemaHall(cinemaHall);
        LocalDateTime dateTime = LocalDateTime.parse(dto.getLocalTime(),
                DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        movieSession.setShowTime(dateTime);
        return movieSession;
    }

    @Override
    public MovieSessionResponseDto parseToDto(MovieSession movieSession) {
        MovieSessionResponseDto dto = new MovieSessionResponseDto();
        dto.setId(movieSession.getId());
        dto.setMovieId(movieSession.getMovie().getId());
        dto.setTitle(movieSession.getMovie().getTitle());
        dto.setCinemaHallId(movieSession.getCinemaHall().getId());
        dto.setLocalTime(movieSession.getShowTime().toString());
        return dto;
    }
}
