package project.cinema.service.impl;

import java.time.LocalDate;
import java.util.List;
import project.cinema.dao.MovieSessionDao;
import project.cinema.lib.Inject;
import project.cinema.lib.Service;
import project.cinema.model.MovieSession;
import project.cinema.service.MovieSessionService;

@Service
public class MovieSessionServiceImpl implements MovieSessionService {
    @Inject
    private MovieSessionDao movieSessionDao;

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        return movieSessionDao.findAvailableSessions(movieId, date);
    }

    @Override
    public MovieSession add(MovieSession session) {
        return movieSessionDao.add(session);
    }
}
