package railTrips.data.contracts;

public interface FunctionsContract {

    interface View {
        void onCalculateRouteDistance(final String outputDistance);
        void onNumberOfTrips(final String outputTrips);
    }

    interface Presenter {
        void calculateRouteDistance(final String inputRoute);
        void numberOfTrips(final String startCity, final String endCity, final int maxStops);
    }

}
