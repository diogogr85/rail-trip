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

    @Override
    public void calculateRouteDistance(String inputRoute) {
        final FunctionsContract.View view = getView();
        if (view != null) {
            final List<Connection> routeInput = ParserHelper.getInstance().parseRoutes(inputRoute);
            final String distance = distanceToTravel(routeInput);

            view.onCalculateRouteDistance(distance);
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

}
