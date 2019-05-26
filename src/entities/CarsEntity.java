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

    /**
     * funcja realizująca zapytanie pobierające dane pojazdów z bazy danych
     * @return
     * @throws SQLException
     * @throws IOException
     */
    public ResultSet getCars() throws SQLException, IOException {

        String query = "SELECT c.id as lp, c.name, c.class as carClass, c.brand, IF((SELECT COUNT(r.id) " +
                "FROM rents r " +
                "WHERE r.car_id = c.id AND" +
                " NOW() BETWEEN start_date AND end_date LIMIT 1) = 0,'wolny','zarezerwowany') as is_rented," +
                " c.localisation, c.price_per_day FROM cars c;";
        System.out.println(query);
        ResultSet carsData = sqlConnector.getData(query);
        return carsData;
    }

    /**
     * funkcja pobierająca dane pojazdu z bazy danych na podstawie przesłanego id pojazdu w parametrze
     * @param carId
     * @return
     */
    public ResultSet getCar(Integer carId) {
        String query = "SELECT" +
                "    c.id as lp," +
                "    c.name ," +
                "    c.class AS carClass," +
                "    c.brand," +
                "    c.localisation" +
                " FROM" +
                "    cars c WHERE c.id = " + carId;
        System.out.println(query);
        ResultSet carData = sqlConnector.getData(query);
        return carData;

    }

    /**
     * funckja pobierająca pełna nazwę samochodu na podstawie przesłanego id pojazdu w parametrze
     * @param carId
     * @return
     * @throws SQLException
     */
    public String getCarName(Integer carId) throws SQLException {
        String query = "SELECT CONCAT(c.name, ' ', c.brand) as name FROM cars c WHERE c.id = " + carId;
        ResultSet carNameSet = sqlConnector.getData(query);
        carNameSet.next();
        return carNameSet.getString("name");
    }

}
