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
            PreparedStatement preparedStatement = connection.prepareStatement("call insert_data_from_user(?,?,?,?);");
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
            CallableStatement callableStatement = connection.prepareCall("select autoruzation (?,?)");
            callableStatement.setString(1, enterToPage.getEmail());
            callableStatement.setString(2, enterToPage.getPassword());
            ResultSet resultSet = callableStatement.executeQuery();
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
            CallableStatement callableStatement = connection.prepareCall("call collectUser(?)");
            callableStatement.setString(1,email);
            ResultSet resultSet = callableStatement.executeQuery();
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

    public User getUser(Integer id){
        User user = new User();
        try {
            CallableStatement callableStatement = connection.prepareCall("call get_user_by_id(?)");
            callableStatement.setString(1, String.valueOf(id));
            ResultSet resultSet = callableStatement.executeQuery();
            resultSet.next();
            user.setUser_id(id);
            user.setName(resultSet.getString(1));
            user.setSurname(resultSet.getString(2));
            user.setEmail(resultSet.getString(3));

        } catch (SQLException e) {
            System.out.println("Ошибка при получении User из метода GetUser ");
        }

        return user;
    }

    public User update(int id, User user){
        try {
            PreparedStatement preparedStatement =connection.prepareStatement("call update_data_on_idUser(?,?,?,?)");
            preparedStatement.setString(1,user.getName());
            preparedStatement.setString(2,user.getSurname());
            preparedStatement.setString(3,user.getEmail());
            preparedStatement.setString(4, String.valueOf(id));
            preparedStatement.executeUpdate();
            user.setUser_id(id);
        } catch (SQLException e) {
            System.out.println("Ошибка в обновлении пользователя");
        }
        return user;
    }
}











