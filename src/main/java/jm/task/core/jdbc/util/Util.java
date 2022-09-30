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
    private static Util util;

    private Util() {
    }

    public static Util getUtil() {
        if (util == null) {
            util = new Util();
        }
        return util;
    }

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration()
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory();
        }
        return sessionFactory;
    }

    public Connection getConnect() {
        try {
            return connect = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

