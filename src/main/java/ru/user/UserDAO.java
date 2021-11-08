package ru.user;

import org.springframework.stereotype.Component;

import java.sql.*;

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

    public void addUser(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users(name,surname,email,password) VALUES (?,?,?,?);");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка");
        }
    }

    public boolean checkUser(EnterToPage enterToPage) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE email = ? and password = ?");
            preparedStatement.setString(1, enterToPage.getEmail());
            preparedStatement.setString(2, enterToPage.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();

        } catch (SQLException e) {
            System.out.println("Пользователь не существует");
            System.out.println("Ошибка");
        }
        return false;
    }
    public User getUser(String email){
        User user = new User();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT name, surname, user_id FROM users WHERE email = ?");
            preparedStatement.setString(1,email);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            user.setEmail(email);
            user.setName(resultSet.getString(1));
            user.setSurname(resultSet.getString(2));
            user.setUser_id(Integer.parseInt(resultSet.getString(3)));

        } catch (SQLException e) {
            System.out.println("Ошибка при получении User");
        }

        return user;
    }
}











