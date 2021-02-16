package project.cinema.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import project.cinema.model.MovieSession;

public interface MovieSessionDao {
    List<MovieSession> findAvailableSessions(Long movieId, LocalDate date);

    MovieSession add(MovieSession session);

    void update(MovieSession movieSession);

    void delete(Long id);

    Optional<MovieSession> getById(Long id);
}
