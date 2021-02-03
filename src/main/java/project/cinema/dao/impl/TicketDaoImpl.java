package project.cinema.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import project.cinema.dao.TicketDao;
import project.cinema.exception.DataProcessingException;
import project.cinema.lib.Dao;
import project.cinema.model.Ticket;
import project.cinema.util.HibernateUtil;

@Dao
public class TicketDaoImpl implements TicketDao {
    @Override
    public Ticket add(Ticket ticket) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(ticket);
            transaction.commit();
            return ticket;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert ticket: " + ticket + " in DB", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
