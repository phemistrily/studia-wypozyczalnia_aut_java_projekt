/**
 * Connection types:
 * - getConnection() is default via localhost
 */

package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SqlConnector {
    public static Connection conn;
    private static ResultSet response;
    private static Statement statement;

    public Connection getConnection(String connectionName) {

        switch (connectionName)
        {
            case "localhost":
                String dbName="rent_cars_app";
                String user="root";
                String password="";

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");

                    conn = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName+"?serverTimezone=UTC", user , password);
                    statement = conn.createStatement();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                System.out.println("brak polaczenia");
                break;
        }

        return conn;
    }

    public static ResultSet getData(String query)
    {
        try
        {
            response = statement.executeQuery(query);
            System.out.println("records from database");

        } catch (Exception ex)
        {
            System.out.println(ex);
        }
        return response;
    }
}
