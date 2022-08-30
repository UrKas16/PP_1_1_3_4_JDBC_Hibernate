package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import javax.persistence.Column;
import javax.sql.RowSet;
import javax.sql.rowset.JdbcRowSet;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() throws SQLException {

    }

    public void createUsersTable() {
        try (Statement stat = Util.getConnection().createStatement()) {
            stat.executeUpdate("CREATE TABLE user_data (Id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(20), " +
                    "lastName VARCHAR(20), age TINYINT)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void dropUsersTable() {
        try (Statement stat = Util.getConnection().createStatement()) {
            stat.executeUpdate("CREATE TABLE IF NOT EXISTS user_data (Id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                    "name VARCHAR(20), lastName VARCHAR(20), age TINYINT)");
            stat.executeUpdate("DROP TABLE user_data");
        } catch (SQLException e) {
            System.out.printf("%s - ошибка удаления таблицы user_data", e.getErrorCode());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Statement stat = Util.getConnection().createStatement()) {
            stat.executeUpdate("INSERT INTO user_data (name, lastname, age) VALUES (name, lastname, age)");
        } catch (SQLException e) {
            System.out.printf("%s - ошибка добавления данных в таблицу user_data", e.getErrorCode());
        }
    }

    public void removeUserById(long id) {
        try (Statement stat = Util.getConnection().createStatement()) {
            stat.executeUpdate("DELETE from user_data WHERE Id = id");
        } catch (SQLException e) {
            System.out.printf("%s - ошибка удаления данных из таблицы user_data", e.getErrorCode());
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        Long id = 0L;
        String name = "";
        String lastName = "";
        Byte age = 0;


        try (Statement stat = Util.getConnection().createStatement()) {
            ResultSet set = stat.executeQuery("SELECT * FROM user_data");

            while (set.next()) {
                id = set.getLong("Id");
                name = set.getString("name");
                lastName = set.getString("lastname");
                age = set.getByte("age");
                User user = new User(name, lastName, age);
                user.setId(id);

                userList.add(user);
            }

            return userList;

        } catch (SQLException e) {
            System.out.printf("%s - ошибка получения данных из таблицы user_data", e.getErrorCode());
        }
        return null;
    }

    public void cleanUsersTable() {
        try (Statement stat = Util.getConnection().createStatement()) {
            stat.executeUpdate("TRUNCATE TABLE user_data");
        } catch (SQLException e) {
            System.out.printf("%s - ошибка удаления данных из таблицы user_data", e.getErrorCode());
        }
    }
}
