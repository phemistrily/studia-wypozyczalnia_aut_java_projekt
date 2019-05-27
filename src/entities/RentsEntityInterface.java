package entities;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface RentsEntityInterface {
    ResultSet getRents() throws SQLException, IOException;

    void createRent(String bookDate, String returnDate, Integer carId, String returnCity) throws SQLException;
}
