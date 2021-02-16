package project.cinema.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import project.cinema.model.MovieSession;

public interface MovieSessionService {
    List<MovieSession> findAvailableSessions(Long movieId, LocalDate date);

    MovieSession add(MovieSession session);

    void update(MovieSession movieSession);

    void delete(Long id);

    MovieSession getById(Long id);
}
