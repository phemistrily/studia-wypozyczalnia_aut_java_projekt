package controllers;

import entities.UserEntity;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoggedInController implements Initializable {

    private String loginData;
    @FXML
    private GridPane loggedInPanel;
    @FXML
    private Button loggedInLogOut;
    @FXML
    private Label loggedInWelcomeLabel;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
    }

    public void initData(String login) {
        this.loginData = login;
        this.loggedInWelcomeLabel.setText("Witaj " + this.loginData);
    }

    public void logoutAction(javafx.event.ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../fxmlData/main.fxml"));
        GridPane pane = loader.load();
        /**
         * Set scene and pass data through the scenes
         */
        System.out.println(loginData);
        loginData = null;
        loggedInPanel.getChildren().setAll(pane);
    }
}
