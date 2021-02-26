package project.football.service;

import java.util.List;
import project.football.model.Game;

public interface GameService {
    Game add(Game movie);

    List<Game> getAll();

    Game get(Long id);
}
