package railTrips.data.parsers;

import com.google.gson.Gson;
import railTrips.data.models.Connection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Parser {

    private final HashMap<String, Connection> mList;
    private final Gson gson;

    public Parser() {
        mList = new HashMap<>();
        gson = new Gson();
    }

    public void evaluateLine(final String dataLine) {
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


        System.out.println(gson.toJson(mList));
    }

}
