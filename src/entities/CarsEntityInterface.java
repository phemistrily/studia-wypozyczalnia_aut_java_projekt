package entities;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface CarsEntityInterface {
    ResultSet getCars() throws SQLException, IOException;

    ResultSet getCar(Integer carId);

    String getCarName(Integer carId) throws SQLException;

    String getPriceForRent(Integer carId) throws SQLException;
}
