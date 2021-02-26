package project.football.service.impl;

import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;
import project.football.dao.GameSessionDao;
import project.football.model.GameSession;
import project.football.service.GameSessionService;

@Service
public class GameSessionServiceImpl implements GameSessionService {
    private final GameSessionDao gameSessionDao;

    public GameSessionServiceImpl(GameSessionDao gameSessionDao) {
        this.gameSessionDao = gameSessionDao;
    }

    @Override
    public List<GameSession> findAvailableSessions(Long movieId, LocalDate date) {
        return gameSessionDao.findAvailableSessions(movieId, date);
    }

    @Override
    public GameSession add(GameSession session) {
        return gameSessionDao.add(session);
    }

    @Override
    public void update(GameSession gameSession) {
        gameSessionDao.update(gameSession);
    }

    @Override
    public void delete(Long id) {
        gameSessionDao.delete(id);
    }

    @Override
    public GameSession getById(Long id) {
        return gameSessionDao.getById(id).get();
    }
}
