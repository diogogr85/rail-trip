package railTrips.data.presenters;

import railTrips.data.contracts.FunctionsContract;
import railTrips.data.models.Connection;
import railTrips.data.parsers.ParserHelper;
import railTrips.utils.Constants;

import java.util.List;

public class FunctionsPresenter extends BasePresenter<FunctionsContract.View> implements FunctionsContract.Presenter {

    public FunctionsPresenter() {
        final String data = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";

        ParserHelper parser = ParserHelper.getInstance();
        parser.evaluateData(data);
    }

    /**
     * Routes distance
     */
    @Override
    public void calculateRouteDistance(String inputRoute) {
        final FunctionsContract.View view = getView();
        if (view != null) {
            final List<Connection> routeInput = ParserHelper.getInstance().parseRoutes(inputRoute);
            final String distance = distanceToTravel(routeInput);

            view.onOutputSuccess(distance);
        }
    }

    private String distanceToTravel(List<Connection> routeInput) {
        int distance = 0;

        Connection currentConnection = routeInput.get(0);
        for (int i = 1; i < routeInput.size(); i++) {
            Connection nextConnection = routeInput.get(i);

            Integer value = currentConnection.getCitiesTo().get(nextConnection.getCityFrom());
            if (value != null) {
                distance += value;
            } else {
                return Constants.TRIP_ROUTE_INVALID;
            }
            currentConnection = routeInput.get(i);
        }

        return String.valueOf(distance);
    }
    /** End - Routes distance */

    /**
     * Number of trips
     */
    @Override
    public void numberOfTrips(String startCity, String endCity, int maxStops) {
        final FunctionsContract.View view = getView();
        if (view != null) {
            final Connection startConnection = ParserHelper.getInstance().getConnections().get(startCity);

            final int tripsCounter = countRoutes(startConnection, endCity, maxStops);
            view.onOutputSuccess(String.valueOf(tripsCounter));
        }
    }

    private int countRoutes(Connection startConnection, String endCity, int maxStops) {

        int counter = 0;
        for (String city : startConnection.getCitiesAsList()) {
            if (endCity.equals(city)) {
                counter += 1;
            } else {
                if (maxStops == 1) {
                    counter += 0;
                } else {
                    counter += 0 + countRoutes(ParserHelper.getInstance().getConnections().get(city), endCity, maxStops - 1);
                }
            }
        }

        return counter;
    }
    /** End - Number of trips */

    /**
     * find trips
     */
    @Override
    public void findTrips(String startCity, String endCity, int stops) {
        final FunctionsContract.View view = getView();
        if (view != null) {
            final Connection startConnection = ParserHelper.getInstance().getConnections().get(startCity);

            final int counterPaths = findRoutes(startConnection, endCity, stops);
            view.onOutputSuccess(String.valueOf(counterPaths));
        }
    }



    private int findRoutes(Connection startConnection, String endCity, int stops) {

        int counter = 0;
        for (String city : startConnection.getCitiesAsList()) {
            if (stops == 1) {
                if (endCity.equals(city)) {
                    counter += 1;
                } else {
                    counter += 0;
                }
            } else {
                counter += findRoutes(ParserHelper.getInstance().getConnections().get(city), endCity, stops - 1);
            }
        }

        return counter;
    }

}
