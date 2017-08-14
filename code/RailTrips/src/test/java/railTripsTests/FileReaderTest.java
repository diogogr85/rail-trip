package railTripsTests;

import org.junit.Test;
import railTrips.data.loaders.FileManager;

import java.io.File;

public class FileReaderTest {

    @Test
    public void readFile() {
        final String fileName = "src/main/resources/data.txt";
        FileManager.newInstance().prepareData(fileName);
    }

}
