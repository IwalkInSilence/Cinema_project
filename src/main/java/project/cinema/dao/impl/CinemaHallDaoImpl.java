package project.cinema.dao.impl;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import project.cinema.dao.CinemaHallDao;
import project.cinema.exception.DataProcessingException;
import project.cinema.lib.Dao;
import project.cinema.model.CinemaHall;
import project.cinema.util.HibernateUtil;

@Dao
public class CinemaHallDaoImpl implements CinemaHallDao {
    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(cinemaHall);
            transaction.commit();
            return cinemaHall;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert cinemaHall: " + cinemaHall, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<CinemaHall> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<CinemaHall> getAllCinemaHallsQuery = session.createQuery(
                    "from CinemaHall ", CinemaHall.class);
            return getAllCinemaHallsQuery.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't find all Movies in DB", e);
        }
    }
}

