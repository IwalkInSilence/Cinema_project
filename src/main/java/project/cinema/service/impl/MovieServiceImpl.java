package project.cinema.service.impl;

import java.util.List;
import project.cinema.dao.MovieDao;
import project.cinema.lib.Inject;
import project.cinema.lib.Service;
import project.cinema.model.Movie;
import project.cinema.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {
    @Inject
    private MovieDao movieDao;

    @Override
    public Movie add(Movie movie) {
        return movieDao.add(movie);
    }

    @Override
    public List<Movie> getAll() {
        return movieDao.getAll();
    }
}
