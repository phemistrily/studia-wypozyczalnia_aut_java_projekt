package entities;

import controllers.SqlConnector;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RentsEntity {
    SqlConnector sqlConnector;

    public RentsEntity() {
        sqlConnector = new SqlConnector();
        sqlConnector.getConnection("localhost");
    }

    public ResultSet getRents() throws SQLException, IOException {
        String query = "SELECT" +
                "    r.id as lp," +
                "    c.name ," +
                "    r.start_date," +
                "    r.end_date," +
                "    r.start_city," +
                "    r.end_city" +
                " FROM" +
                "    rents r" +
                " INNER JOIN cars c ON c.id = r.car_id";
        System.out.println(query);
        ResultSet rentsData = sqlConnector.getData(query);
        return rentsData;
    }
}