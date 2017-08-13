package railTrips.data.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Connection {

    private String mCityFrom;
    private final HashMap<String, Integer> mCitiesTo;   //key -> city name; value -> distance between cities

    public Connection() {
        mCitiesTo = new HashMap<>();
    }

    public String getCityFrom() {
        return mCityFrom;
    }

    public void setCityFrom(String cityFrom) {
        mCityFrom = cityFrom;
    }

    public HashMap<String, Integer> getCitiesTo() {
        return mCitiesTo;
    }

    public void addConnection(final String city, final int distanceValue) {
        mCitiesTo.put(city, distanceValue);
    }

    public List<String> getCitiesAsList() {
        return new ArrayList<String>(mCitiesTo.keySet());
    }

}
