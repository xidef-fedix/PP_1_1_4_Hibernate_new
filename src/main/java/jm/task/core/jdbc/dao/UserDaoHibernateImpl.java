package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.loader.custom.sql.SQLCustomQuery;

import java.util.List;

public class UserDaoHibernateImpl extends Util implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        getSession().beginTransaction();
        getSession().createSQLQuery("CREATE TABLE IF NOT EXISTS users " +
                "(id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                " name VARCHAR(255)," +
                " lastName VARCHAR(255)," +
                " age TINYINT);").executeUpdate();
        getSession().getTransaction().commit();
        getSession().close();

    }

    @Override
    public void dropUsersTable() {
        getSession().beginTransaction();
        getSession().createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();
        getSession().getTransaction().commit();
        getSession().close();

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        getSession().beginTransaction();
        getSession().save(user);
        getSession().getTransaction().commit();
        getSession().close();

    }

    @Override
    public void removeUserById(long id) {
        getSession().beginTransaction();
        User user = new User();
        user = getSession().get(User.class, id);
        getSession().delete(user);
        getSession().getTransaction().commit();
        getSession().close();
    }

    @Override
    public List<User> getAllUsers() {
        getSession().beginTransaction();
        List <User> userList = getSession().createQuery("from User").getResultList();
        getSession().getTransaction().commit();
        getSession().close();
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        getSession().beginTransaction();
        getSession().createSQLQuery("TRUNCATE TABLE users").executeUpdate();
        getSession().getTransaction().commit();
        getSession().close();

    }
}
