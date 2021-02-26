package project.football.dao.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import project.football.dao.GameSessionDao;
import project.football.exception.DataProcessingException;
import project.football.model.GameSession;

@Repository
public class GameSessionDaoImpl implements GameSessionDao {
    private final SessionFactory sessionFactory;

    public GameSessionDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<GameSession> findAvailableSessions(Long gameId, LocalDate date) {
        try (Session session = sessionFactory.openSession()) {
            Query<GameSession> movieSessionQuery
                    = session.createQuery("from GameSession gs "
                            + "join fetch gs.game "
                            + "join fetch gs.stadium "
                            + "where gs.game.id = :gameId "
                            + "and date_format(gs.showTime, '%Y-%m-%d') = :date",
                    GameSession.class)
                    .setParameter("gameId", gameId)
                    .setParameter("date", date.toString());
            return movieSessionQuery.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get all movie sessions by id: "
                    + gameId + " on this date: " + date, e);
        }
    }

    @Override
    public GameSession add(GameSession gameSession) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(gameSession);
            transaction.commit();
            return gameSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert game session: " + gameSession, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void update(GameSession gameSession) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(gameSession);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't update game session: " + gameSession, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void delete(Long id) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            GameSession gameSession = session.load(GameSession.class, id);
            session.remove(gameSession);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't delete game session with id: " + id, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<GameSession> getById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(GameSession.class, id));
        } catch (Exception e) {
            throw new DataProcessingException("Can't get Game Session by id:" + id, e);
        }
    }
}
