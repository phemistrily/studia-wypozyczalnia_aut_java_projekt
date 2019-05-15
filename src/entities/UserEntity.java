package entities;

import controllers.SqlConnector;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserEntity {

    SqlConnector sqlConnector;

    public UserEntity() {
        sqlConnector = new SqlConnector();
        sqlConnector.getConnection("localhost");
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
    public boolean loginUser(String login, String password) throws SQLException, IOException {

        System.out.println(login);
        System.out.println(password);
        String query = "SELECT email, password FROM users WHERE active = 1 AND email = '" + login + "' AND password = '" + password + "'";
        System.out.println(query);
        ResultSet userData = sqlConnector.getData(query);
        System.out.println(userData);
        userData.last();
        int size = userData.getRow(); //check user exist
        if (size == 0)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Nie poprawny login lub hasło");
            alert.setHeaderText("Nie poprawny login lub hasło");
            alert.setContentText("Nie poprawny login lub hasło");

            alert.showAndWait();
            return false;
        }
        else {
            return true;
        }
    }

}
