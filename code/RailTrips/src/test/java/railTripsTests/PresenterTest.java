package railTripsTests;

import org.junit.Test;
import railTrips.data.contracts.FunctionsContract;
import railTrips.data.presenters.FunctionsPresenter;
import railTrips.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class PresenterTest {

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
        presenter.countTrips("A", "C", 4);
    }

    @Test
    public void shortestLength() {
        FunctionsPresenter presenter = new FunctionsPresenter();

        FunctionsContract.View view = new FunctionsContract.View() {
            @Override
            public void onOutputSuccess(String output) {
                System.out.println(String.format(Constants.OUTPUT_ANWSER_PREFIX, output));
            }
        };
        presenter.bindView(view);

        //startCity, endCity, maxStops
        presenter.findShortestRouteLength("A", "C");
    }

}
