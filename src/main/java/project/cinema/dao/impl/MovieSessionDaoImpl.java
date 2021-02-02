package project.cinema.dao.impl;

import java.time.LocalDate;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import project.cinema.dao.MovieSessionDao;
import project.cinema.exception.DataProcessingException;
import project.cinema.lib.Dao;
import project.cinema.model.MovieSession;
import project.cinema.util.HibernateUtil;

@Dao
public class MovieSessionDaoImpl implements MovieSessionDao {
    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<MovieSession> movieSessionQuery
                    = session.createQuery("from MovieSession ms "
                            + "join fetch ms.movie "
                            + "join fetch ms.cinemaHall "
                            + "where ms.movie.id = :movieId "
                            + "and date_format(ms.showTime, '%Y-%m-%d') = :date",
                    MovieSession.class)
                    .setParameter("movieId", movieId)
                    .setParameter("date", date.toString());
            return movieSessionQuery.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get all movie sessions by id: "
                    + movieId + " on this date: " + date, e);
        }
    }

    @Override
    public MovieSession add(MovieSession movieSession) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(movieSession);
            transaction.commit();
            return movieSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert movie session: " + movieSession, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
