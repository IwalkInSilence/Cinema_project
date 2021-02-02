package project.cinema.service;

import java.util.List;
import project.cinema.model.CinemaHall;

public interface CinemaHallService {
    CinemaHall add(CinemaHall cinemaHall);

    List<CinemaHall> getAll();
}