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
                "    c.id as lp," +
                "    c.name ," +
                "    c.class AS carClass," +
                "    c.brand," +
                "    c.is_rented," +
                "    c.localisation" +
                " FROM" +
                "    cars c";
        System.out.println(query);
        ResultSet carsData = sqlConnector.getData(query);
        return carsData;
    }

    public ResultSet getCar(String carId) {
        if (!carId.equals(""))
        {
            String query = "SELECT" +
                    "    c.id as lp," +
                    "    c.name ," +
                    "    c.class AS carClass," +
                    "    c.brand," +
                    "    c.is_rented," +
                    "    c.localisation" +
                    " FROM" +
                    "    cars c WHERE c.id = " + carId;
            System.out.println(query);
            ResultSet carData = sqlConnector.getData(query);
            return carData;
        }
        else
        {
            System.out.println("no carId");
            return null;
        }

    }
}
