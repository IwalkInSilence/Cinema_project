package project.cinema.dao.impl;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import project.cinema.dao.MovieDao;
import project.cinema.exception.DataProcessingException;
import project.cinema.lib.Dao;
import project.cinema.model.Movie;
import project.cinema.util.HibernateUtil;

@Dao
public class MovieDaoImpl implements MovieDao {
    @Override
    public Movie add(Movie movie) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(movie);
            transaction.commit();
            return movie;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert movie entity" + movie, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Movie> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Movie> getAllMoviesQuerry = session.createQuery("from Movie ", Movie.class);
            return getAllMoviesQuerry.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't find all Movies in DB", e);
        }
    }
}
