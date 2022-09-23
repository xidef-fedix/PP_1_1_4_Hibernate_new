package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private final String USERNAME = "root";
    private final String PASSWORD = "89888988";
    private final String URL = "jdbc:mysql://localhost:3306/users";
    private Connection connect;
    private SessionFactory factory = new Configuration()
            .addAnnotatedClass(User.class)
            .buildSessionFactory();

    public Session getSession () {
        return factory.getCurrentSession();
    }
    public Connection getConnect() {
        try {
            return connect = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void connectToBase() {
        getConnect();
    }


}

