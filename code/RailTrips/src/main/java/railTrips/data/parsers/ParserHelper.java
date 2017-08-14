package railTrips.data.parsers;

import railTrips.data.models.Connection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ParserHelper {

    private static ParserHelper sInstance;

    private final HashMap<String, Connection> mList;

    private ParserHelper() {
        mList = new HashMap<>();
    }

    public static ParserHelper getInstance() {
        if (sInstance == null) {
            sInstance = new ParserHelper();
        }

        return sInstance;
    }

    /**
     * Connections data
     */
    public void evaluateData(final String dataLine) {
        final String[] nodes = dataLine.trim().split(",");

        for (String node : nodes) {
            final Connection connection = new Connection();
            final String city = node.trim().substring(0,1);

            final Connection con = mList.get(city);
            try {
                if (con == null) {
                    connection.setCityFrom(city);
                    connection.addConnection(node.trim().substring(1, 2), Integer.parseInt(node.trim().substring(2)));

                    mList.put(city, connection);

                } else {
                    con.addConnection(node.trim().substring(1, 2), Integer.parseInt(node.trim().substring(2)));
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }

    public HashMap<String, Connection> getConnections() {
        return mList;
    }
    /** End - Connections data */

    /**
     * Parse functions
     */
    public List<Connection> parseRoutes(final String routes) {
        final String[] citiesPassing = routes.split("-");

        final List<Connection> routeInput = new ArrayList<>();
        for (String city : citiesPassing) {
            routeInput.add(mList.get(city));
        }

        return routeInput;
    }

}
