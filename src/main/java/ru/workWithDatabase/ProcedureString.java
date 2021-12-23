package ru.workWithDatabase;

import java.util.ArrayList;
import java.util.List;

public class ProcedureString {
    public static final String INSERTDATAFROMUSER = "CREATE PROCEDURE insert_data_from_user(IN names TEXT, surnames TEXT,emails VARCHAR(100) , passwords TEXT)\n" +
            "BEGIN\n" +
            "    INSERT INTO email(email) VALUES (emails);\n" +
            "    INSERT INTO user(name,surname, email_idemal, password) VALUES (names,surnames,find_idEmail(emails),passwords) ;\n" +
            "END;";
    public static final String FUNCTIONFINDIDEMAIL = "CREATE FUNCTION find_idEmail( emaill VARCHAR(100))\n" +
            "RETURNS int\n" +
            "BEGIN\n" +
            "    DECLARE num INT DEFAULT 0;\n" +
            "    select idEmail INTO num FROM email WHERE email = emaill;\n" +
            "    RETURN num;\n" +
            "END;";
    public static final String AUTORUZATION = "CREATE FUNCTION autoruzation(emails VARCHAR(100), passwords TEXT)\n" +
            "    RETURNS int\n" +
            "BEGIN\n" +
            "    DECLARE num INT DEFAULT 0;\n" +
            "    SELECT USER.idUser\n" +
            "    InTO num\n" +
            "    FROM email,\n" +
            "         User\n" +
            "    WHERE email.email = emails\n" +
            "      and User.password = passwords;\n" +
            "    RETURN num;\n" +
            "END;";
    public static final String CREATE_TABLES = "" +
            "CREATE PROCEDURE create_Tables()\n" +
            "BEGIN\n" +
            "    CREATE TABLE IF NOT EXISTS email\n" +
            "    (\n" +
            "        idEmail INT PRIMARY KEY AUTO_INCREMENT,\n" +
            "        email   VARCHAR(60) NOT NULL UNIQUE\n" +
            "    );\n" +
            "\n" +
            "\n" +
            "    CREATE TABLE IF NOT EXISTS request\n" +
            "    ( idRequest INT PRIMARY KEY AUTO_INCREMENT,\n" +
            "      request_to_server VARCHAR(60)\n" +
            "    );\n" +
            "\n" +
            "    CREATE TABLE IF NOT EXISTS books\n" +
            "    ( idbooks INT PRIMARY KEY AUTO_INCREMENT,\n" +
            "      image_book_source VARCHAR(100),\n" +
            "      image_url VARCHAR(100),\n" +
            "      request_idrequest INT ,\n" +
            "      FOREIGN KEY (request_idrequest) REFERENCES request (idRequest)\n" +
            "    );\n" +
            "    CREATE TABLE IF NOT EXISTS USER\n" +
            "    (idUser INT PRIMARY KEY AUTO_INCREMENT,\n" +
            "     Name TEXT,\n" +
            "     Surname TEXT,\n" +
            "     email_idemal INT,\n" +
            "     FOREIGN KEY (email_idemal) REFERENCES email (idEmail),\n" +
            "     password TEXT\n" +
            "    );\n" +
            "    CREATE TABLE if not exists user_connect_books\n" +
            "    (\n" +
            "        user_id INT NOT NULL,\n" +
            "        book_id INT PRIMARY KEY,\n" +
            "        FOREIGN KEY (user_id) REFERENCES USER (idUser),\n" +
            "        FOREIGN KEY (book_id) REFERENCES books (idbooks)\n" +
            "    );\n" +
            "\n" +
            "END;\n";
    public static final String DROPALLTABLES = "CREATE PROCEDURE drop_all_tables()\n" +
            "BEGIN\n" +
            "    DROP TABLE IF EXISTS user_connect_books,USER,email,books, request;\n" +
            "END;";



    public static final String COLLECTUSER = "create\n" +
            "    procedure collectUser(IN emails varchar(100))\n" +
            "BEGIN\n" +
            "    SELECT User.name, User.surname, User.idUser\n" +
            "    FROM User,\n" +
            "         email\n" +
            "    WHERE email.email = emails;\n" +
            "END;\n";
    public static final String getUserById = "create\n" +
            "    procedure get_user_by_id(IN ids int)\n" +
            "BEGIN\n" +
            "    SELECT User.name, User.surname, email.email FROM User, email WHERE idUser = ids;\n" +
            "END;\n";

    public static final String UPDATEDATAONIDUSER = "CREATE PROCEDURE update_data_on_idUser(names TEXT, surnames TEXT, emails VARCHAR(100), user_id INT)\n" +
            "BEGIN\n" +
            "UPDATE User, email SET User.name = names, User.surname=surnames, email.email=emails WHERE User.idUser = user_id;\n" +
            "END;";


    public static final String get_books_of_user = "create\n" +
            "    procedure get_books_of_user(IN par_user_id int)\n" +
            "BEGIN\n" +
            "    SELECT books.image_url,books.image_book_source,request.request_to_server,books.idbooks\n" +
            "    FROM books\n" +
            "             INNER JOIN request\n" +
            "                        ON request.idrequest = books.request_idrequest\n" +
            "             INNER JOIN user_connect_books ucb\n" +
            "                        ON books.idbooks = ucb.book_id\n" +
            "            Where ucb.user_id = par_user_id;\n" +
            "\n" +
            "END;\n" +
            "\n";


    public static final String DELETE_BOOK = "create\n" +
            " procedure delete_book(IN par_book_id int)\n" +
            "BEGIN\n" +
            "    DELETE FROM user_connect_books WHERE book_id = par_book_id;\n" +
            "    DELETE FROM books WHERE idbooks = par_book_id;\n" +
            "    DELETE FROM request WHERE idrequest = par_book_id;\n" +
            "END;\n";

    public static final String find_IDRequest = "create\n" +
            "     function find_idrequest(par_request_to_server varchar(100)) returns int\n" +
            "BEGIN\n" +
            "    DECLARE num INT DEFAULT 0;\n" +
            "    select idrequest INTO num FROM request WHERE request_to_server = par_request_to_server;\n" +
            "    RETURN num;\n" +
            "END;\n";

    public static final String insertBook = "create\n" +
            " procedure insert_book(IN url varchar(100), IN sourceImage varchar(100),\n" +
            "                                                   IN doreg varchar(100), IN par_idUser int)\n" +
            "BEGIN\n" +
            "    INSERT INTO request (request_to_server) VALUES (doreg);\n" +
            "    INSERT INTO books (image_url, image_book_source,request_idrequest) VALUES (url,sourceImage, find_idrequest(doreg));\n" +
            "    INSERT INTO user_connect_books VALUES (par_idUser,(SELECT idbooks FROM books WHERE image_url = url LIMIT 1));\n" +
            "\n" +
            "END;";

    public static final String select_book_on_inbook = "create\n" +
            "    procedure select_book_on_idbook(IN book_id int)\n" +
            "BEGIN\n" +
            "    SELECT b.image_url,b.image_book_source,r.request_to_server FROM books b, request r WHERE b.idbooks= book_id;\n" +
            "END;\n" +
            "\n";

    public static final String deleteBooksTable = "CREATE PROCEDURE drop_books_table()\n" +
            "BEGIN\n" +
            "DROP TABLE IF EXISTS user_connect_books;\n" +
            "DROP TABLE IF EXISTS books;\n" +
            "DROP TABLE IF EXISTS request;\n" +
            "END;";

    public static final List<String> listOfNamesProcedure = new ArrayList(List.of("insert_data_from_user", "bucketUser", "drop_all_tables", "create_Tables",
            "get_user_by_id", "update_data_on_idUser","get_books_of_user","select_book_on_idbook","insert_book","collectUser","delete_book","drop_books_table"));
    public static final List<String> listOfNamesFunctions = new ArrayList(List.of("find_idEmail", "autoruzation","find_idrequest"));
    public static final List<String> listOfProcedure = new ArrayList(List.of(insertBook,select_book_on_inbook,INSERTDATAFROMUSER, CREATE_TABLES, DROPALLTABLES,deleteBooksTable, COLLECTUSER, getUserById,
             UPDATEDATAONIDUSER,get_books_of_user,DELETE_BOOK));
    public static final List<String> listOfFunction = new ArrayList(List.of(FUNCTIONFINDIDEMAIL, AUTORUZATION,find_IDRequest));


}
