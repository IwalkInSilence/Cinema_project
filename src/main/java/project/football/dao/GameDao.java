package project.football.dao;

import java.util.List;
import project.football.model.Game;

public interface GameDao {
    Game add(Game movie);

    List<Game> getAll();

    Game get(Long id);
}
