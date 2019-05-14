package entities;

import controllers.SqlConnector;

import java.sql.ResultSet;

public class UserEntity {
    SqlConnector sqlConnector;
    public UserEntity() {
        sqlConnector = new SqlConnector();
        sqlConnector.getConnection("localhost");

        System.out.println("polaczenie udalo sie");
    }
    public void registerUser(String login, String password) {
        System.out.println(login);
        System.out.println(password);

//        String connectionName = "localhost";
//        ConfigClass configClass = new ConfigClass(connectionName);

//        try {
//            String sql="INSERT INTO users (email, password, active) VALUES ('" + login +"' ,'" + password + "',1)";
//            Connection conn;
//            Statement statement=conn.createStatement();
//            statement.executeUpdate(sql);
//    }
    }

    public void loginUser(String login, String password){

        System.out.println(login);
        System.out.println(password);
        String query = "SELECT email, password FROM users WHERE active = 1 AND email = '" + login + "' AND password = '" + password + "'";
        System.out.println(query);
        ResultSet userData = sqlConnector.getData(query);
        System.out.println(userData);
//            String connectionName = "localhost";
////            ConfigClass configClass = new ConfigClass(connectionName);
////
////        try {
////            String sql="SELECT email, password FROM users WHERE active = 1 AND email = '" + login + "' AND password = '" + password + "'";
////            Connection conn;
////            Statement statement=conn.createStatement();
////            statement.executeUpdate(sql);
////        }
    }
}
