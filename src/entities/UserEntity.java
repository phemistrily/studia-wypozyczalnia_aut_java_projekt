package entities;

import controllers.ConfigClass;

import java.sql.Connection;
import java.sql.Statement;

public class UserEntity {
    public void registerUser(String login, String password) {
        System.out.println(login);
        System.out.println(password);

        String connectionName = "localhost";
        ConfigClass configClass = new ConfigClass(connectionName);

        try {
            String sql="INSERT INTO users (email, password, active) VALUES ('" + login +"' ,'" + password + "',1)";
            Connection conn;
            Statement statement=conn.createStatement();
            statement.executeUpdate(sql);
    }

    public void loginUser(String login, String password) {
        System.out.println(login);
        System.out.println(password);

        String connectionName = "localhost";
        ConfigClass configClass = new ConfigClass(connectionName);

        try {
            String sql="SELECT email, password FROM users WHERE active = 1 AND email = '" + login + "' AND password = '" + password + "'";
            Connection conn;
            Statement statement=conn.createStatement();
            statement.executeUpdate(sql);
        }
}
