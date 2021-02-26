package project.football.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import project.football.model.GameSession;

public interface GameSessionDao {
    List<GameSession> findAvailableSessions(Long movieId, LocalDate date);

    GameSession add(GameSession session);

    void update(GameSession gameSession);

    void delete(Long id);

    Optional<GameSession> getById(Long id);
}
