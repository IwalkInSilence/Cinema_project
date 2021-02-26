package project.football.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import project.football.dao.ShoppingCartDao;
import project.football.exception.DataProcessingException;
import project.football.model.ShoppingCart;
import project.football.model.User;

@Repository
public class ShoppingCartDaoImpl implements ShoppingCartDao {
    private final SessionFactory sessionFactory;

    public ShoppingCartDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public ShoppingCart add(ShoppingCart shoppingCart) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(shoppingCart);
            transaction.commit();
            return shoppingCart;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert shoppingCart: "
                    + shoppingCart + " in DB", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public ShoppingCart getByUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            Query<ShoppingCart> shoppingCartQuery = session.createQuery(
                    "select sc from ShoppingCart sc "
                    + "left join fetch sc.tickets t "
                    + "left join fetch t.movieSession ms "
                    + "left join fetch ms.movie "
                    + "left join fetch ms.cinemaHall "
                    + "where sc.user = :user", ShoppingCart.class)
                    .setParameter("user", user);
            return shoppingCartQuery.getSingleResult();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get Shopping Cart for user: " + user, e);
        }
    }

    @Override
    public void update(ShoppingCart shoppingCart) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(shoppingCart);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't update shoppingCart: "
                    + shoppingCart + " in DB", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
