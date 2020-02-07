import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, CsvValidationException {
        // Literally just calls our parser right now (....and is called for tests)
        CsvParser csvP = new CsvParser("src/Data/SEOExample.csv");
        csvP.printCsv();


    }
}