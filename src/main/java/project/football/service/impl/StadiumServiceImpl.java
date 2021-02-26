package project.football.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import project.football.dao.StadiumDao;
import project.football.model.Stadium;
import project.football.service.StadiumService;

@Service
public class StadiumServiceImpl implements StadiumService {
    private final StadiumDao stadiumDao;

    public StadiumServiceImpl(StadiumDao stadiumDao) {
        this.stadiumDao = stadiumDao;
    }

    @Override
    public Stadium add(Stadium stadium) {
        return stadiumDao.add(stadium);
    }

    @Override
    public List<Stadium> getAll() {
        return stadiumDao.getAll();
    }

    @Override
    public Stadium get(Long id) {
        return stadiumDao.get(id);
    }
}
