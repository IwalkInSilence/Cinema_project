package project.football.dao.impl;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import project.football.dao.StadiumDao;
import project.football.exception.DataProcessingException;
import project.football.model.Stadium;

@Repository
public class StadiumDaoImpl implements StadiumDao {
    private final SessionFactory sessionFactory;

    public StadiumDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Stadium add(Stadium stadium) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(stadium);
            transaction.commit();
            return stadium;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert stadium: " + stadium, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Stadium> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Stadium> getAllCinemaHallsQuery = session.createQuery(
                    "from Stadium ", Stadium.class);
            return getAllCinemaHallsQuery.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get all stadiums from DB", e);
        }
    }

    @Override
    public Stadium get(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Stadium.class, id);
        } catch (Exception e) {
            throw new DataProcessingException("Can't get stadium with id: " + id, e);
        }
    }
}
