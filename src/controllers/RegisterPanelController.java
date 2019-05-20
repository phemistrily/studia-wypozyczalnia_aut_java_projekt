package controllers;

import entities.UserEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TextField;

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
    private TextField loginField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField passwordFieldAgain;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //
    }

    @FXML
    public void registerAction(ActionEvent actionEvent) throws SQLException, IOException {
        String login = loginField.getText();
        String password = passwordField.getText();
        String repeatPassword = passwordFieldAgain.getText();

        UserEntity user = new UserEntity();
        Boolean isRegistered = user.registerUser(login,password,repeatPassword);
        if (isRegistered)
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../fxmlData/loggedIn.fxml"));
            AnchorPane pane = loader.load();
            /**
             * Set scene and pass data through the scenes
             */
            LoggedInController logggedInController = loader.getController();
            logggedInController.initData(login);
            registerFormPanel.getChildren().setAll(pane);
        }
    }

    @FXML
    public void getWelcomePanel(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../fxmlData/main.fxml"));
        AnchorPane pane = loader.load();
        /**
         * Set scene and pass data through the scenes
         */
        registerFormPanel.getChildren().setAll(pane);
    }

    @FXML
    public void getCatalogView(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../fxmlData/carView.fxml"));
        AnchorPane pane = loader.load();
        /**
         * Set scene and pass data through the scenes
         */
        registerFormPanel.getChildren().setAll(pane);
    }

}
