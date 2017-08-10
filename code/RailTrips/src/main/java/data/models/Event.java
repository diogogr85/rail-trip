package data.models;

import java.util.HashMap;

public class Event {

    private String mCityFrom;
    private final HashMap<String, Integer> mCitiesTo;   //key -> city name; value -> distance between cities

    public Event(String cityFrom) {
        mCityFrom = cityFrom;
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
}
