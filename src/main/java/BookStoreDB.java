import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
// just to check if these are found

public class BookStoreDB {

    static Connection conn = null;


    public static void connectToDB() {
        try {
            conn =
                    DriverManager.getConnection(
                            "jdbc:sqlite:/home/dory/Downloads/DataIteration/src/Data/BookStore.db");

            System.out.println("Connection Successful ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void insertIntoAuthor(String name, String email, String url) {
        try {
            PreparedStatement stmnt =
                    conn.prepareStatement(
                            "INSERT INTO author(author_name, author_email, author_url) VALUES (?,?,?)");
            stmnt.setString(1, name);
            stmnt.setString(2, email);
            stmnt.setString(3, url);
            stmnt.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertIntoBook(Object isbn, Object publisher_name, Object author_name, Object book_title) throws IOException, CsvValidationException {

        Reader read = Files.newBufferedReader(Paths.get("src/Data/bookstore_report2.csv"));
        CSVReader reader = new CSVReader(read);

        String[] column;

        while ((column = reader.readNext()) != null) {
            try {
                String isbn_1 = String.valueOf(isbn);
                String publisher_name_1 = String.valueOf(publisher_name);
                String author_name_1 = String.valueOf(author_name);
                String book_title_1 = String.valueOf(book_title);
                PreparedStatement stmt =
                        conn.prepareStatement(
                                "INSERT INTO book(isbn, publisher_name, author_name, book_title) VALUES (?,?,?,?)");
                stmt.setString(1, isbn_1);
                stmt.setString(2, publisher_name_1);
                stmt.setString(3, author_name_1);
                stmt.setString(4, book_title_1);
                stmt.execute();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}