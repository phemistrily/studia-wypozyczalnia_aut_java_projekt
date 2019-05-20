package entities;

import controllers.SqlConnector;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CarsEntity {

    SqlConnector sqlConnector;

    public CarsEntity() {
        sqlConnector = new SqlConnector();
        sqlConnector.getConnection("localhost");
    }

    public ResultSet getCars() throws SQLException, IOException {

        String query = "SELECT" +
                "    c.id," +
                "    c.name AS nazwa," +
                "    c.class AS klasa," +
                "    c.brand AS marka," +
                "    c.is_rented as czy_wynajety," +
                "    c.city_where_is_car as lokalizacja" +
                "FROM" +
                "    cars c";
        //System.out.println(query);
        ResultSet carsData = sqlConnector.getData(query);
        return carsData;
    }
}
