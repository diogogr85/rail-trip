package railTrips.data.contracts;

public interface FunctionsContract {

    interface View {
        void onOutputSuccess(final String output);
    }

    interface Presenter {
        void calculateRouteDistance(final String inputRoute);
        void numberOfTrips(final String startCity, final String endCity, final int maxStops);
        void findTrips(final String startCity, final String endCity, final int stops);
    }

}
