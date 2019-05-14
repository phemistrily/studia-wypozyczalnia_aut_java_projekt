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
    private Connection conn;
    private ResultSet response;
    private Statement statement;

    public void getConnection(String connectionName) {
        System.out.println(connectionName);
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
                    System.out.println("trening");
                    System.out.println(response);
                    System.out.println(statement);

                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                System.out.println("brak polaczenia");
                break;

        }
    }

    public ResultSet getData(String query)
    {
        System.out.println("getData");
        System.out.println(response);
        System.out.println(statement);
        try
        {
            response = statement.executeQuery(query);
            System.out.println("records from database");

        } catch (Exception ex)
        {
            System.out.println(ex);
            System.out.println("dupa");
        }
        return response;
    }
}
