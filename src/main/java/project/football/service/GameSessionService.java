package project.football.service;

import java.time.LocalDate;
import java.util.List;
import project.football.model.GameSession;

public interface GameSessionService {
    List<GameSession> findAvailableSessions(Long movieId, LocalDate date);

    GameSession add(GameSession session);

    void update(GameSession gameSession);

    void delete(Long id);

    GameSession getById(Long id);
}
