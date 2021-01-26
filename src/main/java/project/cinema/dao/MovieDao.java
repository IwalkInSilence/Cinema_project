package project.cinema.dao;

import java.util.List;
import project.cinema.model.Movie;

public interface MovieDao {
    Movie add(Movie movie);

    List<Movie> getAll();
}
