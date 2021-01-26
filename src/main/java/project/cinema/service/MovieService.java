package project.cinema.service;

import java.util.List;
import project.cinema.model.Movie;

public interface MovieService {
    Movie add(Movie movie);

    List<Movie> getAll();
}
