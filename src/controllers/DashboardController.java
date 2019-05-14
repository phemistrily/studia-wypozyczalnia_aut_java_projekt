package controllers;

import entities.UserEntity;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DashboardController implements Initializable
{
    public TextField loginLabel;
    public PasswordField passwordField;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
    }
    @FXML
    public void loginAction(ActionEvent actionEvent) throws SQLException, IOException {
        String login = loginLabel.getText();
        String password = passwordField.getText();
        UserEntity user = new UserEntity();
        user.loginUser(login, password);
    }
}