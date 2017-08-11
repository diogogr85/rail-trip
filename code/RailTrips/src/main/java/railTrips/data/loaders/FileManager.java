package railTrips.data.loaders;

import railTrips.data.models.Connections;

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

    public void readFile(final String filePath) {
        try {
            final FileReader reader = new FileReader(filePath);
            final BufferedReader buffer = new BufferedReader(reader);

            String line;

            final List<Connections> railData = new ArrayList<>();
            while ((line = buffer.readLine()) != null) {
                System.out.println(line);
            }
            buffer.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
