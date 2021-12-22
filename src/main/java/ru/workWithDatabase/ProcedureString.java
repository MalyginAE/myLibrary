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
    public static final String AUTORUZATION = "CREATE FUNCTION autoruzation( emails VARCHAR(100), passwords TEXT)\n" +
            "    RETURNS int\n" +
            "BEGIN\n" +
            "    DECLARE num INT DEFAULT 0;\n" +
            "    SELECT USER.idUser InTO num FROM email, User WHERE email.email = emails and User.password = passwords;\n" +
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
    public static final String COLLECTUSER = "CREATE PROCEDURE bucketUser(IN emails VARCHAR(100))\n" +
            "BEGIN\n" +
            "    SELECT User.name, User.surname, User.idUser FROM User,email WHERE email.email = emails;\n" +
            "END;";
    public static final String getUserById = "CREATE PROCEDURE get_user_by_id(ids INT)\n" +
            "BEGIN\n" +
            "    SELECT User.name, User.surname, email.email FROM User, email WHERE idUser = ids;\n" +
            "END;";

    public static final String UPDATEDATAONIDUSER = "CREATE PROCEDURE update_data_on_idUser(names TEXT, surnames TEXT, emails VARCHAR(100), user_id INT)\n" +
            "BEGIN\n" +
            "UPDATE User, email SET User.name = names, User.surname=surnames, email.email=emails WHERE User.idUser = user_id;\n" +
            "END;";

    public static final String updateDataOnUser =
            "CREATE PROCEDURE update_data_on_idUser(names TEXT, surnames TEXT, emails VARCHAR(100), user_id INT)\n" +
                    "BEGIN\n" +
                    "    UPDATE User, email SET User.name = names, User.surname=surnames, email.email=emails WHERE User.idUser = user_id;\n" +
                    "END;";

    public static final String get_books_of_user = "CREATE PROCEDURE get_books_of_user(par_user_id INT)\n" +
            "    BEGIN\n" +
            "    SELECT books.image_url,books.image_book_source,request.request_to_server,books.idbooks\n" +
            "    FROM books\n" +
            "    INNER JOIN request\n" +
            "    ON request.idrequest = request.idRequest\n" +
            "    INNER JOIN user_connect_books ucb\n" +
            "    ON books.idbooks = ucb.book_id\n" +
            "    INNER JOIN User\n" +
            "    ON ucb.user_id = User.idUser\n" +
            "            AND\n" +
            "    user.idUser = par_user_id;\n" +
            "\n" +
            "    END;";


    public static final List<String> listOfNamesProcedure = new ArrayList(List.of("insert_data_from_user", "bucketUser", "drop_all_tables", "create_Tables",
            "autoruzation", "find_idEmail", "update_data_on_idUser","get_books_of_user"));
    public static final List<String> listOfNamesFunctions = new ArrayList(List.of("find_idEmail", "autoruzation"));
    public static final List<String> listOfProcedure = new ArrayList(List.of(INSERTDATAFROMUSER, FUNCTIONFINDIDEMAIL, AUTORUZATION, CREATE_TABLES, DROPALLTABLES, COLLECTUSER, getUserById, updateDataOnUser
            , UPDATEDATAONIDUSER,get_books_of_user));


}
