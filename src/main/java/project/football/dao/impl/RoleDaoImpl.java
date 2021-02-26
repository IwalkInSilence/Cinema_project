package project.football.dao.impl;

import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import project.football.dao.RoleDao;
import project.football.exception.DataProcessingException;
import project.football.model.Role;
import project.football.model.RoleName;

@Repository
public class RoleDaoImpl implements RoleDao {
    private final SessionFactory sessionFactory;

    public RoleDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Role role) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(role);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't add Role: " + role + " in DB", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<Role> getRoleByName(String roleName) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Role where role = :roleName", Role.class)
                     .setParameter("roleName", RoleName.valueOf(roleName))
                     .uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get Role by name:" + roleName, e);
        }
    }
}
