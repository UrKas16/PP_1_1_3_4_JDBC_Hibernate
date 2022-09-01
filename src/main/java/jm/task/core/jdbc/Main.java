package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        User userIvan = new User("Иван", "Иванов", (byte) 12);
        User userPetr = new User("Петр", "Петров", (byte) 25);
        User userBoris = new User("Борис", "Кузьмин", (byte) 45);
        User userJhon = new User("Джон", "Смит", (byte) 55);

        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser(userIvan.getName(), userIvan.getLastName(), userIvan.getAge());
        userService.saveUser(userPetr.getName(), userPetr.getLastName(), userPetr.getAge());
        userService.saveUser(userBoris.getName(), userBoris.getLastName(), userBoris.getAge());
        userService.saveUser(userJhon.getName(), userJhon.getLastName(), userJhon.getAge());

        List<User> userList = userService.getAllUsers();

        for (User us : userList) {
            System.out.println(us.toString());
        }

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
