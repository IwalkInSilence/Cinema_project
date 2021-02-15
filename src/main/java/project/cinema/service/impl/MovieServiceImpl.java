package project.cinema.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import project.cinema.dao.MovieDao;
import project.cinema.model.Movie;
import project.cinema.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieDao movieDao;

    public MovieServiceImpl(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    @Override
    public Movie add(Movie movie) {
        return movieDao.add(movie);
    }

    @Override
    public List<Movie> getAll() {
        return movieDao.getAll();
    }

    @Override
    public Movie get(Long id) {
        return movieDao.get(id);
    }
}
