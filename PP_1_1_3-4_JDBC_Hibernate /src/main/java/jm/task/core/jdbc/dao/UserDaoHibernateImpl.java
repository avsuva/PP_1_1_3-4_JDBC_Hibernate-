package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.util.Util;
import org.hibernate.SessionFactory;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try {
            SessionFactory sessionFactory = Util.getHibernateSession();
            Session session = sessionFactory.getCurrentSession();;
            transaction = session.beginTransaction();
            session.createSQLQuery(
                    "CREATE TABLE IF NOT EXISTS users (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(20) NOT NULL, lastName VARCHAR(20), age INT NOT NULL)")
                    .addEntity(User.class).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try {
            SessionFactory sessionFactory = Util.getHibernateSession();
            Session session = sessionFactory.getCurrentSession();;
            transaction = session.beginTransaction();
            session.createSQLQuery(
                    "DROP TABLE IF EXISTS users"
            )
                    .executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try {
            SessionFactory sessionFactory = Util.getHibernateSession();
            Session session = sessionFactory.getCurrentSession();;
            transaction = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try {
            SessionFactory sessionFactory = Util.getHibernateSession();
            Session session = sessionFactory.getCurrentSession();;
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction = null;
        try {
            SessionFactory sessionFactory = Util.getHibernateSession();
            Session session = sessionFactory.getCurrentSession();;
            transaction = session.beginTransaction();
            List <User> list = session.createQuery("SELECT u FROM User u", User.class)
                    .getResultList();
            transaction.commit();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return null;
        }
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try {
            SessionFactory sessionFactory = Util.getHibernateSession();
            Session session = sessionFactory.getCurrentSession();;
            transaction = session.beginTransaction();
            session.createSQLQuery(
                            "DELETE FROM users"
                    )
                    .executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }
}
