package railTrips.data.contracts;

public interface FunctionsContract {

    interface View {
        void onCalculateRouteDistance(final String outputDistance);
    }

    interface Presenter {
        void calculateRouteDistance(final String inputRoute);
    }

}
