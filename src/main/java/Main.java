import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;


public class Main {


    static Connection conn = null;

    public static void main(String[] args) throws IOException, CsvValidationException {
        // Literally just calls our parser right now (....and is called for tests)
        BookStoreDB.connectToDB();
        BookStoreDB database = new BookStoreDB();


        CsvParser csvP = new CsvParser("src/Data/bookstore_report2.csv");
        csvP.printCsv();


        Gson gson = new Gson();
        JsonReader jread = new JsonReader(new FileReader("src/Data/authors.json"));
        AuthorParser[] authors = gson.fromJson(jread, AuthorParser[].class);


        for (var element : authors) {
            String name = element.getName();
            String email = element.getEmail();
            String url = element.getUrl();

            database.insertIntoAuthor(name, email, url);
        }


    }
}