package project.football.service;

import java.util.List;
import project.football.model.Stadium;

public interface StadiumService {
    Stadium add(Stadium stadium);

    List<Stadium> getAll();

    Stadium get(Long id);
}
