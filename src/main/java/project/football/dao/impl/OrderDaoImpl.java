package project.football.dao.impl;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import project.football.dao.OrderDao;
import project.football.exception.DataProcessingException;
import project.football.model.Order;
import project.football.model.User;

@Repository
public class OrderDaoImpl implements OrderDao {
    private final SessionFactory sessionFactory;

    public OrderDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Order add(Order order) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
            return order;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert order: "
                    + order + " in DB", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Order> getOrderByUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            Query<Order> orderQuery = session.createQuery("select distinct o "
                            + "from Order o "
                            + "left join fetch o.tickets t "
                            + "left join fetch o.user "
                            + "left join fetch t.movieSession ms "
                            + "left join fetch ms.cinemaHall "
                            + "left join fetch ms.movie "
                            + "where o.user = :user", Order.class)
                    .setParameter("user", user);
            return orderQuery.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get all orders by User: "
                    + user, e);
        }
    }
}
