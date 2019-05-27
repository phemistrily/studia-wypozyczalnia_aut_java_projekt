package controllers;

import java.sql.Connection;

public class ConfigClass implements SqlInterface {
    public String mainTitle="Wypożyczalnia aut";

    public ConfigClass (String connectionName) {
        switch (connectionName) {
            case "localhost":
                SqlConnector sqlConnector = new SqlConnector();
                sqlConnector.getConnection("localhost");
                break;
            default:

                break;
        }
    }

    @Override
    public SqlConnector getLocalhostConnection() {
        SqlConnector sqlConnector = new SqlConnector();
        sqlConnector.getConnection("localhost");
        return sqlConnector;
    }
}
