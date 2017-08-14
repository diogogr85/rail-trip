package railTripsTests;

import org.junit.Test;
import railTrips.data.contracts.FunctionsContract;
import railTrips.data.presenters.FunctionsPresenter;
import railTrips.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class PresenterTest {

    @Test
    public void calculateDirectDistance() {
        final String data = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
        FunctionsPresenter presenter = new FunctionsPresenter(data);

        FunctionsContract.View view = new FunctionsContract.View() {
            @Override
            public void onOutputSuccess(String output) {
                System.out.println(String.format(Constants.OUTPUT_ANWSER_PREFIX, 0, output));
//                assertEquals(9, Integer.parseInt(output));    //route: A-B-C
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
        final String data = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
        FunctionsPresenter presenter = new FunctionsPresenter(data);

        FunctionsContract.View view = new FunctionsContract.View() {
            @Override
            public void onOutputSuccess(String output) {
                System.out.println(String.format(Constants.OUTPUT_ANWSER_PREFIX, 0, output));
                assertEquals(2, Integer.parseInt(output));
            }
        };
        presenter.bindView(view);

        //startCity, endCity, maxStops
        presenter.numberOfTrips("C", "C", 3);
    }

    @Test
    public void findTrips() {
        final String data = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
        FunctionsPresenter presenter = new FunctionsPresenter(data);

        FunctionsContract.View view = new FunctionsContract.View() {
            @Override
            public void onOutputSuccess(String output) {
                System.out.println(String.format(Constants.OUTPUT_ANWSER_PREFIX, 0, output));
                assertEquals(3, Integer.parseInt(output));
            }
        };
        presenter.bindView(view);

        //startCity, endCity, maxStops
        presenter.countTrips("A", "C", 4);
    }

    @Test
    public void shortestLength() {
        final String data = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
        FunctionsPresenter presenter = new FunctionsPresenter(data);

        FunctionsContract.View view = new FunctionsContract.View() {
            @Override
            public void onOutputSuccess(String output) {
                System.out.println(String.format(Constants.OUTPUT_ANWSER_PREFIX, 0, output));
                assertEquals(9, Integer.parseInt(output));
            }
        };
        presenter.bindView(view);

        //startCity, endCity, maxStops
        presenter.findShortestRouteLength("A", "C");
    }

    @Test
    public void differentRoutes() {
        final String data = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
        FunctionsPresenter presenter = new FunctionsPresenter(data);

        FunctionsContract.View view = new FunctionsContract.View() {
            @Override
            public void onOutputSuccess(String output) {
                System.out.println(String.format(Constants.OUTPUT_ANWSER_PREFIX, 0, output));
//                assertEquals(7, Integer.parseInt(output));
            }
        };
        presenter.bindView(view);

        presenter.differentRoutes("C", "C", 30);
    }

}
