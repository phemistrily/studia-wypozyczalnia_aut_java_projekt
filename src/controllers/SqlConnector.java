/**
 * Connection types:
 * - getConnection() is default via localhost
 */

package controllers;

import java.sql.*;

public class        SqlConnector {
    private Connection conn;
    private ResultSet response;
    private Statement statement;

    public void getConnection(String connectionName) {
        System.out.println(connectionName);
        switch (connectionName)
        {
            case "localhost":
                String dbName="baza61505_dawid";
                String user="admin61505_dawid";
                String password="6HzbIu5^bH";

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");

                    conn = DriverManager.getConnection("jdbc:mysql://61505.m.tld.pl:3306/" + dbName+"?serverTimezone=UTC", user , password);
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
    }

    public void insertData(String query)
    {
        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getData(String query)
    {
        System.out.println(response);
        System.out.println(statement);
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
