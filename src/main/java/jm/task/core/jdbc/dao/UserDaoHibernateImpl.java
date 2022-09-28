package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.loader.custom.sql.SQLCustomQuery;

import java.util.List;

public class UserDaoHibernateImpl extends Util implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = getSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS users " +
                    "(id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                    " name VARCHAR(255)," +
                    " lastName VARCHAR(255)," +
                    " age TINYINT);").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = getSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        try (Session session = getSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void removeUserById(long id) {
        User user = new User();
        try (Session session = getSession()) {
            Transaction transaction = session.beginTransaction();
            user = session.get(User.class, id);
            session.delete(user);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = null;
        try (Session session = getSession()) {
            Transaction transaction = session.beginTransaction();
            userList = session.createQuery("from User").getResultList();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = getSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("TRUNCATE TABLE users").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
