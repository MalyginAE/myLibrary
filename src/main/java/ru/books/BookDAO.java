package ru.books;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
        addDataInDataBase(filePath, url, id, doRequestText(filePath, id, multipartFile));


    }
    public String doRequestText(String path,int id, MultipartFile multipartFile){
        return "/images/user_id/"+id+"/"+multipartFile.getOriginalFilename();
    }

    public boolean addDataInDataBase(String sourceImage, String url, int id, String doReq) {
        try {
            PreparedStatement addInBooks = connection.prepareStatement("INSERT INTO books (book_url, image_book_source,request_to_server) VALUES ( ?,?,?) ;");
            addInBooks.setString(1, url);
            addInBooks.setString(2, sourceImage);
            addInBooks.setString(3, doReq);
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

    public List<Book> allUserBook(int user_id){
        List<Book> list = new ArrayList<>();
        try {
            PreparedStatement addInBooks = connection.prepareStatement("SELECT book_url,image_book_source,request_to_server,books.book_id FROM user_connect_books ucb INNER JOIN books ON  user_id = ? and ucb.book_id=books.book_id;");
            addInBooks.setString(1, String.valueOf(user_id));
            ResultSet resultSet = addInBooks.executeQuery();

            while (resultSet.next()){
                Book book = new Book();
                book.setUrlPDF(resultSet.getString("book_url"));
                book.setImage_source(resultSet.getString("image_book_source"));
                book.setUrl_req(resultSet.getString("request_to_server"));
                book.setBook_id(Integer.parseInt(resultSet.getString("book_id")));
                list.add(book);
            }

        } catch (SQLException e) {
            System.out.println("При отобажении книг+ "+list.size());
        }
        return list;
    }
    void deleteBookPC(String path){
        try {
            Files.delete(Path.of(path));
        } catch (IOException e) {
            System.out.println("File не удалился");
            e.printStackTrace();
        }

    }
    public Book showDataBook(int book_id){
        try {
            PreparedStatement addInBooks = connection.prepareStatement("SELECT book_url,image_book_source,request_to_server FROM books WHERE book_id = ?;");
            addInBooks.setString(1, String.valueOf(book_id));
            ResultSet resultSet = addInBooks.executeQuery();

            resultSet.next();
                Book book = new Book();
                book.setUrlPDF(resultSet.getString("book_url"));
                book.setImage_source(resultSet.getString("image_book_source"));
                book.setUrl_req(resultSet.getString("request_to_server"));
                book.setBook_id(book_id);
                return book;

        } catch (SQLException e) {
            System.out.println("Ошибка при выплнении запроса данных книги");
        }
        return new Book();
    }
    public void deleteBookData(int book_id){
        System.out.println("Зашли в deleteBookData");
        Book book = showDataBook(book_id);
        System.out.println(book.getImage_source());
        System.out.println(book.message_delete());
        deleteBookPC(book.getImage_source());
        try {

            PreparedStatement delete1 = connection.prepareStatement("DELETE FROM user_connect_books WHERE book_id = ?; ");
            delete1.setString(1, String.valueOf(book_id));
            delete1.executeUpdate();
            PreparedStatement delete2 = connection.prepareStatement("DELETE FROM user_connect_books WHERE book_id = ?; ");
            delete2.setString(1, String.valueOf(book_id));
            delete2.executeUpdate();


        } catch (SQLException e) {
            System.out.println("Удаление книги не удалось");
            e.printStackTrace();
        }
    }
}
