package project.cinema.service.impl;

import org.springframework.stereotype.Component;
import project.cinema.model.Movie;
import project.cinema.model.dto.MovieRequestDto;
import project.cinema.model.dto.MovieResponseDto;
import project.cinema.service.MovieMapper;

@Component
public class MovieMapperImpl implements MovieMapper {

    @Override
    public Movie parseFromDto(MovieRequestDto dto) {
        Movie movie = new Movie();
        movie.setTitle(dto.getTitle());
        movie.setDescription(dto.getDescription());
        return movie;
    }

    @Override
    public MovieResponseDto parseToDto(Movie movie) {
        MovieResponseDto dto = new MovieResponseDto();
        dto.setId(movie.getId());
        dto.setTitle(movie.getTitle());
        dto.setDescription(movie.getDescription());
        return dto;
    }
}
