package entities;

import controllers.SqlConnector;
import models.UserSession;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RentsEntity {
    SqlConnector sqlConnector;

    /**
     * konstruktor klasy inicjalizujacy połączoenie z bazaa danych
     */
    public RentsEntity() {
        sqlConnector = new SqlConnector();
        sqlConnector.getConnection("localhost");
    }

    /**
     * funckjcja pobierająca listę rezerwacji pojazdów z bazy danych
     * @return
     * @throws SQLException
     * @throws IOException
     */
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

    public void createRent(String bookDate, String returnDate, Integer carId, String returnCity) throws SQLException {
        String createRentQuery = "INSERT INTO rents (car_id, start_date, end_date, start_city, end_city, user_id, cash_per_day)" +
                " SELECT " + carId + ",'" + bookDate + " 00:00:00','" +
                returnDate + " 00:00:00',localisation,'" + returnCity + "'," + UserSession.getInstace("").getUserId() +
                ", price_per_day FROM cars WHERE id = " + carId;
        System.out.println(createRentQuery);
        sqlConnector.insertData(createRentQuery);
    }
}
