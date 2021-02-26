package project.football.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import project.football.dao.GameDao;
import project.football.model.Game;
import project.football.service.GameService;

@Service
public class GameServiceImpl implements GameService {
    private final GameDao gameDao;

    public GameServiceImpl(GameDao gameDao) {
        this.gameDao = gameDao;
    }

    @Override
    public Game add(Game movie) {
        return gameDao.add(movie);
    }

    @Override
    public List<Game> getAll() {
        return gameDao.getAll();
    }

    @Override
    public Game get(Long id) {
        return gameDao.get(id);
    }
}
