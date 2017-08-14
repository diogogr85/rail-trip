package railTripsTests;

import org.junit.Test;
import railTrips.data.contracts.FunctionsContract;
import railTrips.data.models.Connection;
import railTrips.data.parsers.ParserHelper;
import railTrips.data.presenters.FunctionsPresenter;
import railTrips.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class ParserTest {

    @Test
    public void initDataTest() {
        final String data = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";

        ParserHelper parser = ParserHelper.getInstance();
        parser.evaluateData(data);
    }

    @Test
    public void parseRoutes() {
        final String data = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";

        ParserHelper parser = ParserHelper.getInstance();
        parser.evaluateData(data);

        final List<Connection> routesInput = parser.parseRoutes("A-E-B-C-D");
    }

}
