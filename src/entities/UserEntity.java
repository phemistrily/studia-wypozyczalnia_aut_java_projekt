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
    private String login;
    private String password;

    public UserEntity() {
        sqlConnector = new SqlConnector();
        sqlConnector.getConnection("localhost");
    }

    private void setLogin(String login) {
        this.login = login;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    public void registesrUser(String login, String password) {
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

        this.setLogin(login);
        this.setPassword(password);
        String query = "SELECT email, password FROM users WHERE active = 1 AND email = '" + this.login + "' AND password = '" + this.password + "'";
        //System.out.println(query);
        ResultSet userData = sqlConnector.getData(query);
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

    public Boolean registerUser(String login, String password, String repeatPassword) throws SQLException {
        this.setLogin(login);
        if(password != repeatPassword)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Hasła nie są takie same");
            alert.setHeaderText("Hasła nie są takie same");
            alert.setContentText("Hasła nie są takie same");

            alert.showAndWait();
            return false;
        }
        String checkUserExistQuery = "SELECT email FROM users WHERE email = '" + login + "'";
        ResultSet copiedUsersEntity = sqlConnector.getData(checkUserExistQuery);
        copiedUsersEntity.last();
        int size = copiedUsersEntity.getRow();
        if(size == 0)
        {

            return true;
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Nie zarejestrowano");
            alert.setHeaderText("Użytkownik istnieje w systemie");
            alert.setContentText("Użytkownik istnieje w systemie");
            return false;
        }
    }

}
