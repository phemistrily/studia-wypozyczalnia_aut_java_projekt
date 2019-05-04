package controllers;

import java.sql.Connection;

public class ConfigClass {
    public String mainTitle="Wypożyczalnia aut";

    public ConfigClass (String connectionName) {
        switch (connectionName) {
            case "localhost":
                SqlConnector sqlConnector = new SqlConnector();
                Connection conn = sqlConnector.getConnection();
                break;
            default:

                break;
        }
    }
}
