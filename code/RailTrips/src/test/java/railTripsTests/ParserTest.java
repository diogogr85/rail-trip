package railTripsTests;

import org.junit.Test;
import railTrips.data.models.Connection;
import railTrips.data.parsers.Parser;

import java.util.List;

public class ParserTest {

    @Test
    public void initDataTest() {
        final String data = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";

        Parser parser = new Parser();
        parser.evaluateLine(data);

    }

}
