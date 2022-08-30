package jm.task.core.jdbc;

import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        Connection conn = Util.getConnection();

        try {
            Statement sat = conn.createStatement();
           // sat.executeUpdate("CREATE TABLE user_data (Id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
            //        "name VARCHAR(20), lastName VARCHAR(20), age TINYINT)");


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


//        Session session = Util.getSessionFactory().openSession();
//
//        session.close();
//        Util.shutdown();
    }
}
