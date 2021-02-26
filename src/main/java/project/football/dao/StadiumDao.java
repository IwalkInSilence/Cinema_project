package project.football.dao;

import java.util.List;
import project.football.model.Stadium;

public interface StadiumDao {
    Stadium add(Stadium stadium);

    List<Stadium> getAll();

    Stadium get(Long id);
}
