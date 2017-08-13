package railTripsTests;

import com.google.gson.Gson;
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

        Gson gson = new Gson();
        System.out.println(gson.toJson(routesInput));
    }

    @Test
    public void calculateDirectDistance() {
        FunctionsPresenter presenter = new FunctionsPresenter();

        FunctionsContract.View view = new FunctionsContract.View() {
            @Override
            public void onOutputSuccess(String output) {
                System.out.println(String.format(Constants.OUTPUT_ANWSER_PREFIX, output));
            }
        };
        presenter.bindView(view);

        List<String> routes = new ArrayList<>();
        routes.add("A-B-C");
        routes.add("A-D");
        routes.add("A-D-C");
        routes.add("A-E-B-C-D");
        routes.add("A-E-D");

        for (String route : routes) {
            presenter.calculateRouteDistance(route);
        }
    }

    @Test
    public void numberOfTrips() {
        FunctionsPresenter presenter = new FunctionsPresenter();

        FunctionsContract.View view = new FunctionsContract.View() {
            @Override
            public void onOutputSuccess(String output) {
                System.out.println(String.format(Constants.OUTPUT_ANWSER_PREFIX, output));
            }
        };
        presenter.bindView(view);

        //startCity, endCity, maxStops
        presenter.numberOfTrips("C", "C", 3);
    }

    @Test
    public void findTrips() {
        FunctionsPresenter presenter = new FunctionsPresenter();

        FunctionsContract.View view = new FunctionsContract.View() {
            @Override
            public void onOutputSuccess(String output) {
                System.out.println(String.format(Constants.OUTPUT_ANWSER_PREFIX, output));
            }
        };
        presenter.bindView(view);

        //startCity, endCity, maxStops
        presenter.findTrips("A", "B", 4);
    }

}
