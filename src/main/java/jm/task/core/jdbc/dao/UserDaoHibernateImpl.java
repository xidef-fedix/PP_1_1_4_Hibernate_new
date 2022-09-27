package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.loader.custom.sql.SQLCustomQuery;

import java.util.List;

public class UserDaoHibernateImpl extends Util implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try {
            getSession().beginTransaction();
            getSession().createSQLQuery("CREATE TABLE IF NOT EXISTS users " +
                    "(id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                    " name VARCHAR(255)," +
                    " lastName VARCHAR(255)," +
                    " age TINYINT);").executeUpdate();
            getSession().getTransaction().commit();
            getSession().close();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @Override
    public void dropUsersTable() {
        try {
            getSession().beginTransaction();
            getSession().createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();
            getSession().getTransaction().commit();
            getSession().close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        try {
            getSession().beginTransaction();
            getSession().save(user);
            getSession().getTransaction().commit();
            getSession().close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void removeUserById(long id) {
        User user = new User();
        try {
            getSession().beginTransaction();
            user = getSession().get(User.class, id);
            getSession().delete(user);
            getSession().getTransaction().commit();
            getSession().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = null;
        try {
            getSession().beginTransaction();
            userList = getSession().createQuery("from User").getResultList();
            getSession().getTransaction().commit();
            getSession().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        try {
            getSession().beginTransaction();
            getSession().createSQLQuery("TRUNCATE TABLE users").executeUpdate();
            getSession().getTransaction().commit();
            getSession().close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
