package project.cinema.dao;

import java.util.List;
import project.cinema.model.CinemaHall;

public interface CinemaHallDao {
    CinemaHall add(CinemaHall cinemaHall);

    List<CinemaHall> getAll();
}
