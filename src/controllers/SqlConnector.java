/**
 * Connection types:
 * - getConnection() is default via localhost
 */

package controllers;

import java.sql.Connection;
import java.sql.DriverManager;

public class SqlConnector {
    public Connection conn;
    public Connection getConnection() {

        String dbName="rent_cars_app";
        String user="root";
        String password="";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName+"?serverTimezone=UTC", user , password);
        }
        catch (Exception e) {
            e.printStackTrace();
        }


        return conn;
    }
}
