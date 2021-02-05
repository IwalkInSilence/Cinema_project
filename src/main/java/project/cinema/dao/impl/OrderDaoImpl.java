package project.cinema.dao.impl;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import project.cinema.dao.OrderDao;
import project.cinema.exception.DataProcessingException;
import project.cinema.lib.Dao;
import project.cinema.model.Order;
import project.cinema.model.User;
import project.cinema.util.HibernateUtil;

@Dao
public class OrderDaoImpl implements OrderDao {
    @Override
    public Order add(Order order) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
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
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
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
