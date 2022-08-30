package jm.task.core.jdbc.util;

import com.mysql.cj.MysqlConnection;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final Connection conn = buildConnection();
    private static final String url = "jdbc:mysql://localhost:3306/pp_1_1_3_jdbc_hibernate";
    private static final String user = "root";
    private static final String password = "Iqprimer100@";

    public static Connection buildConnection() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        if (conn == null) {
            return buildConnection();
        }
        return conn;
    }


//    private static final SessionFactory sessionFactory = buildSessionFactory();
//    private static SessionFactory buildSessionFactory() {
//        try {
//            return new Configuration().configure(new File("src/main/resources/hibernate.cfg.xml")).buildSessionFactory();
//
//        } catch (Throwable ex) {
//            System.err.println("Initial SessionFactory creation failed." + ex);
//            throw new ExceptionInInitializerError(ex);
//        }
//    }
//
//    public static SessionFactory getSessionFactory() {
//        if (sessionFactory == null) {
//            return buildSessionFactory();
//        }
//        return sessionFactory;
//    }
//
//    public static void shutdown() {
//
//        getSessionFactory().close();
//    }

}
