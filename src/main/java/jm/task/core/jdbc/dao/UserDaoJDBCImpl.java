package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection conn = Util.buildConnection(); Statement stat = conn.createStatement()) {
            stat.executeUpdate("CREATE TABLE IF NOT EXISTS user_data (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(20), lastName VARCHAR(20), age TINYINT)");
        } catch (SQLException e) {
            System.out.printf("%s - ошибка создания таблицы user_data", e.getErrorCode());
        }
    }

    public void dropUsersTable() {
        try (Connection conn = Util.buildConnection(); Statement stat = conn.createStatement()) {
            stat.executeUpdate("CREATE TABLE IF NOT EXISTS user_data (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(20), lastName VARCHAR(20), age TINYINT)");
            stat.executeUpdate("DROP TABLE user_data");
        } catch (SQLException e) {
            System.out.printf("%s - ошибка удаления таблицы user_data", e.getErrorCode());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String query = "INSERT INTO user_data (name, lastName, age) VALUES (?, ?, ?)";

        try (Connection conn = Util.buildConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);
            ps.execute();

            System.out.printf("User с именем – %s добавлен в базу данных\n", name);
        } catch (SQLException e) {
            System.out.printf("%s - ошибка добавления данных в таблицу user_data, %s", e.getErrorCode(), e.getSQLState());
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        String query = "DELETE from user_data WHERE Id = ?";

        try (Connection conn = Util.buildConnection(); PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setLong(1, id);
            ps.execute();
        } catch (SQLException e) {
            System.out.printf("%s - ошибка удаления данных из таблицы user_data", e.getErrorCode());
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        try (Connection conn = Util.buildConnection(); Statement stat = conn.createStatement()) {
            ResultSet rs = stat.executeQuery("SELECT * FROM user_data");

            while (rs.next()) {
                Long id = rs.getLong("Id");
                String name = rs.getString("name");
                String lastName = rs.getString("lastname");
                Byte age = rs.getByte("age");
                User user = new User(name, lastName, age);
                user.setId(id);

                userList.add(user);
            }
        } catch (SQLException e) {
            System.out.printf("%s - ошибка получения данных из таблицы user_data", e.getErrorCode());
        }
        return userList;
    }

    public void cleanUsersTable() {
        try (Connection conn = Util.buildConnection(); Statement stat = conn.createStatement()) {
            stat.executeUpdate("TRUNCATE TABLE user_data");
        } catch (SQLException e) {
            System.out.printf("%s - ошибка удаления данных из таблицы user_data", e.getErrorCode());
        }
    }
}
