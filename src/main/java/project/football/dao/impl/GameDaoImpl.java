package project.football.dao.impl;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import project.football.dao.GameDao;
import project.football.exception.DataProcessingException;
import project.football.model.Game;

@Repository
public class GameDaoImpl implements GameDao {
    private final SessionFactory sessionFactory;

    public GameDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Game add(Game game) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(game);
            transaction.commit();
            return game;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert game entity " + game, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Game> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<Game> getAllMoviesQuerry = session.createQuery("from Game ", Game.class);
            return getAllMoviesQuerry.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't find all Game in DB", e);
        }
    }

    @Override
    public Game get(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Game.class, id);
        } catch (Exception e) {
            throw new DataProcessingException("Can't get Game by id: " + id, e);
        }
    }
}
