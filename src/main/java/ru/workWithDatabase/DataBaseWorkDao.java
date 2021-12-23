package ru.workWithDatabase;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.books.Book;

import javax.annotation.PostConstruct;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataBaseWorkDao {

    private static final String URL = "jdbc:mysql://localhost:3306/library";
    @Value("${user_dataBase_name}")
    private static String USERNAME = "root";
    @Value("${user_dataBase_password}")
    private static String PASSWORD = "root";
    //static Path root = Path.of("C:\\Users\\andre\\library_web_app\\covers");
    private static Connection connection;


    @PostConstruct
    public void dataInitMethod() {
        initDataBaseJDBS();
        createRoutines();



    }

    private void createRoutines() {
        CallableStatement callableStatement = null;
        try {
            for (String s:ProcedureString.listOfNamesProcedure
                 ) {
                callableStatement = connection.prepareCall("DROP PROCEDURE IF EXISTS "+s+";");
                callableStatement.execute();
            }
            for (String s:ProcedureString.listOfNamesFunctions
                 ) {
                callableStatement = connection.prepareCall("drop function if exists "+s+";");
                callableStatement.execute();
            }
            for (String s:ProcedureString.listOfProcedure) {
                callableStatement = connection.prepareCall(s);
                callableStatement.execute();
            }

            for (String s:ProcedureString.listOfFunction) {
                callableStatement = connection.prepareCall(s);
                callableStatement.execute();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initDataBaseJDBS() {
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

    public void dropTables() {
        try {
            connection.prepareCall("call drop_all_tables;").execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void createAllTable(){
        try {
            connection.prepareCall("call create_Tables;").execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createDataBase(){
        try {
            connection.prepareCall("CREATE DATABASE library;").execute();
            connection.prepareCall("USE library;").execute();
            createRoutines();
            createAllTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void deleteDatabase(){
        try {
            connection.prepareCall("DROP DATABASE library;").execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteBooksTables(){
        try {
            connection.prepareCall("call drop_books_table();").execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }





}
