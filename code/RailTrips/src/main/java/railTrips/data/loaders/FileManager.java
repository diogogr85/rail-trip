package railTrips.data.loaders;

import railTrips.data.models.Connection;
import railTrips.data.parsers.ParserHelper;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    private FileManager() {
    }

    public static FileManager newInstance() {
        return new FileManager();
    }

    public void prepareData(final String filePath) {
        try {
            final FileReader reader = new FileReader(filePath);
            final BufferedReader buffer = new BufferedReader(reader);

            String line;

            ParserHelper parser = ParserHelper.getInstance();
            while ((line = buffer.readLine()) != null) {
                parser.evaluateData(line.toUpperCase().trim());
            }
            buffer.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
