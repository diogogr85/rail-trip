package railTripsTests;

import org.junit.Test;
import railTrips.data.loaders.FileManager;

import java.io.File;
import java.nio.file.Paths;

public class FileReaderTest {

    @Test
    public void readFile() {
        File fileDir = new File(".");
        final String fileName = "/assets/data.txt";
        File file = new File(fileDir, fileName);
        FileManager.newInstance().readFile(file.getPath());
    }

}
