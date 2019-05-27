package entities;

import controllers.SqlConnector;
import controllers.SqlInterface;
import javafx.scene.control.Alert;

import javax.xml.transform.Result;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class CarsEntity implements CarsEntityInterface, SqlInterface {

    SqlConnector sqlConnector;

    public CarsEntity() {
        this.sqlConnector = getLocalhostConnection();
    }

    @Override
    public SqlConnector getLocalhostConnection() {
        SqlConnector sqlConnector;
        sqlConnector = new SqlConnector();
        sqlConnector.getConnection("localhost");
        return sqlConnector;
    }

    /**
     * funcja realizująca zapytanie pobierające dane pojazdów z bazy danych
     * @return
     * @throws SQLException
     * @throws IOException
     */
    @Override
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

    public ResultSet getCarsByDate(LocalDate bookDate, LocalDate returnDate) {
        //
        String query = "SELECT c.id as lp, c.name, c.class as carClass, c.brand, IF((SELECT COUNT(r.id) " +
                "FROM rents r " +
                "WHERE r.car_id = c.id AND" +
                " start_date BETWEEN '" + bookDate + " 00:00:00' AND '" + returnDate + " 00:00:00' AND end_date BETWEEN '" + bookDate + " 00:00:00' AND '" + returnDate + " 00:00:00'" + " LIMIT 1) = 0,'wolny','zarezerwowany') as is_rented," +
                " c.localisation, c.price_per_day FROM cars c LEFT JOIN rents r ON r.car_id = c.id ";
        System.out.println(query);
        ResultSet carsByDate = sqlConnector.getData(query);

        return carsByDate;
    }

    /**
     * funkcja pobierająca dane pojazdu z bazy danych na podstawie przesłanego id pojazdu w parametrze
     * @param carId
     * @return
     */
    @Override
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
    @Override
    public String getCarName(Integer carId) throws SQLException {
        String query = "SELECT CONCAT(c.name, ' ', c.brand) as name FROM cars c WHERE c.id = " + carId;
        ResultSet carNameSet = sqlConnector.getData(query);
        carNameSet.next();
        return carNameSet.getString("name");
    }

    @Override
    public String getPriceForRent(Integer carId) throws SQLException {
        String getPriceQuery = "SELECT price_per_day FROM cars WHERE id = " + carId;
        ResultSet priceForRent = sqlConnector.getData(getPriceQuery);
        priceForRent.next();
        return priceForRent.getString("price_per_day");
    }

}
