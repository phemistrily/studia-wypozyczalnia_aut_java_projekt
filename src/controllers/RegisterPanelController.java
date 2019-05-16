package controllers;

import entities.UserEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;

import javax.swing.*;
import javax.xml.soap.Text;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RegisterPanelController implements Initializable {

    @FXML
    private AnchorPane registerFormPanel;
    @FXML
    private TextField loginLabel;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField passwordFieldAgain;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //
    }

    @FXML
    public void registerAction(ActionEvent actionEvent) throws SQLException {
        String login = loginLabel.getText();
        String password = passwordField.getText();
        String repeatPassword = passwordFieldAgain.getText();

        UserEntity user = new UserEntity();
        Boolean isRegistered = user.registerUser(login,password,repeatPassword);
    }

    public void getWelcomePanel(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../fxmlData/main.fxml"));
        AnchorPane pane = loader.load();
        /**
         * Set scene and pass data through the scenes
         */
        registerFormPanel.getChildren().setAll(pane);
    }
}
