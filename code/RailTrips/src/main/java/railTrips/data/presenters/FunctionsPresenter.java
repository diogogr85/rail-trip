package railTrips.data.presenters;

import railTrips.data.contracts.FunctionsContract;
import railTrips.data.models.Connection;
import railTrips.data.parsers.ParserHelper;
import railTrips.utils.Constants;

import java.util.List;

public class FunctionsPresenter extends BasePresenter<FunctionsContract.View> implements FunctionsContract.Presenter {

    private int mShortestLength;

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

            final int tripsCounter = numberOfRoutes(startConnection, endCity, maxStops);
            view.onOutputSuccess(String.valueOf(tripsCounter));
        }
    }

    private int numberOfRoutes(Connection startConnection, String endCity, int maxStops) {
        int counter = 0;
        for (String city : startConnection.getCitiesAsList()) {
            if (endCity.equals(city)) {
                counter += 1;
            } else {
                if (maxStops == 1) {
                    counter += 0;
                } else {
                    counter += 0 + numberOfRoutes(ParserHelper.getInstance().getConnections().get(city), endCity, maxStops - 1);
                }
            }
        }

        return counter;
    }
    /** End - Number of trips */



    /**
     * Counting trips
     */
    @Override
    public void countTrips(String startCity, String endCity, int stops) {
        final FunctionsContract.View view = getView();
        if (view != null) {
            final Connection startConnection = ParserHelper.getInstance().getConnections().get(startCity);

            final int counterPaths = countTripesRoutes(startConnection, endCity, stops);
            view.onOutputSuccess(String.valueOf(counterPaths));
        }
    }

    private int countTripesRoutes(Connection startConnection, String endCity, int stops) {
        int counter = 0;
        for (String city : startConnection.getCitiesAsList()) {
            if (stops == 1) {
                if (endCity.equals(city)) {
                    counter += 1;
                } else {
                    counter += 0;
                }
            } else {
                counter += countTripesRoutes(ParserHelper.getInstance().getConnections().get(city), endCity, stops - 1);
            }
        }

        return counter;
    }
    /** END - Counting trips */

    /**
     * Shortest route length
     */
    @Override
    public void findShortestRouteLength(String startCity, String endCity) {
        final FunctionsContract.View view = getView();
        if (view != null) {
            final Connection startConnection = ParserHelper.getInstance().getConnections().get(startCity);

            mShortestLength = 0;
            routeLength(startConnection, endCity, 0);

            view.onOutputSuccess(mShortestLength != 0 ? String.valueOf(mShortestLength) : Constants.TRIP_ROUTE_INVALID);
        }
    }

    private void routeLength(Connection startConnection, String endCity, int currentLength) {
        try {
            for (int currentIndex = 0; currentIndex < startConnection.getCitiesAsList().size(); currentIndex++) {
                final String city = startConnection.getCitiesAsList().get(currentIndex);
                currentLength += startConnection.getCitiesTo().get(city);

                //stop condition
                if (endCity.equals(city)) {
                    if (mShortestLength == 0 || currentLength < mShortestLength) {
                        mShortestLength = currentLength;
                    }
                    currentLength = 0;

                } else {
                    //if current node has link with the previous connection (variable loop not null), go back one step and take next node to avoid loop
                    //if not, proceed with recurssion
                    Integer loop = ParserHelper.getInstance().getConnections().get(city).getCitiesTo().get(startConnection.getCityFrom());
                    if (loop != null && currentIndex + 1 < startConnection.getCitiesAsList().size()) {
                        currentLength -= startConnection.getCitiesTo().get(city);
                        mShortestLength = 0;
                        currentIndex++;

                        final String nextCity = startConnection.getCitiesAsList().get(currentIndex);
                        currentLength += startConnection.getCitiesTo().get(nextCity);

                        routeLength(ParserHelper.getInstance().getConnections().get(nextCity), endCity, currentLength);

                    } else {
                        routeLength(ParserHelper.getInstance().getConnections().get(city), endCity, currentLength);
                    }
                }
            }

        } catch (StackOverflowError error) {
            mShortestLength = 0;
            return;
        }
    }
    /** END - Shortest route length */

}
