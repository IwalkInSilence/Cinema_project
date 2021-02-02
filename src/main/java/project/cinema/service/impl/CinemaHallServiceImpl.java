package project.cinema.service.impl;

import java.util.List;
import project.cinema.dao.CinemaHallDao;
import project.cinema.lib.Inject;
import project.cinema.lib.Service;
import project.cinema.model.CinemaHall;
import project.cinema.service.CinemaHallService;

@Service
public class CinemaHallServiceImpl implements CinemaHallService {
    @Inject
    private CinemaHallDao cinemaHallDao;

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        return cinemaHallDao.add(cinemaHall);
    }

    @Override
    public List<CinemaHall> getAll() {
        return cinemaHallDao.getAll();
    }
}
