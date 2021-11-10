package ru.books;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class BookDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/library";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    static Path root = Path.of("C:\\Users\\andre\\library_web_app\\src\\main\\resources\\static\\images\\user_id");
    private static Connection connection;

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


    public void upload(MultipartFile multipartFile, String url, int id) throws IOException {
        String filePath = saveOnServer(multipartFile, id);
        addDataInDataBase(filePath, url, id);


    }

    public boolean addDataInDataBase(String sourceImage, String url, int id) {
        try {
            PreparedStatement addInBooks = connection.prepareStatement("INSERT INTO books (book_url, image_book_source,describe_book_source) VALUES ( ?,?,?) ;");
            addInBooks.setString(1, url);
            addInBooks.setString(2, sourceImage);
            addInBooks.setString(3, String.valueOf(id));
            addInBooks.executeUpdate();
            PreparedStatement addInConTabl = connection.prepareStatement("INSERT INTO user_connect_books  VALUES (?,(SELECT book_id FROM books WHERE book_url = ? LIMIT 1));");
            addInConTabl.setString(1, String.valueOf(id));
            addInConTabl.setString(2, url);
            addInConTabl.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка в добавлении данных о книге пользователя");
            return false;
        }
        return true;
    }

    public String saveOnServer(MultipartFile multipartFile, int id) throws IOException {
        Path dir = Paths.get(String.valueOf(root), String.valueOf(id));
        if (!Files.isDirectory(dir))
            Files.createDirectory(dir);
        Path file = Paths.get(String.valueOf(dir), multipartFile.getOriginalFilename());
        if (Files.notExists(file)) {
            Files.createFile(file);
            FileOutputStream stream = null;
            try {
                stream = new FileOutputStream(file.toFile());
                stream.write(multipartFile.getBytes());
            } finally {
                stream.close();
            }
        }
        return file.toString();
    }
}
