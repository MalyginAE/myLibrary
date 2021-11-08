package ru.user;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component

public class UserDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/library";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static Connection connection;
/*
Чтение данных пользователя                          R
метод добавления пользователя при регистрации       C
метод удаления пользователя по login                D
обновления данных пользователя                      U
*/
/////////////////////////////////////////////////////////////
static {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
    try {
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    } catch (SQLException e) {
        System.out.println("Не удалось подключиться");
    }
}
/////////////////////////////////////////////////////////////


}











