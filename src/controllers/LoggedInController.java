package controllers;

import entities.UserEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import models.UserSession;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoggedInController implements Initializable
{

    private String loginData;

    @FXML
    private AnchorPane loggedInPanel;
    @FXML
    private Button loggedInLogOut;
    @FXML
    private Label loggedInWelcomeLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
    }

    public void initData(String login)
    {
        this.loginData = login;
        this.loggedInWelcomeLabel.setText("Witaj " + this.loginData);
    }

    public void logoutAction(javafx.event.ActionEvent actionEvent) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../fxmlData/main.fxml"));
        AnchorPane pane = loader.load();
        /**
         * Set scene and pass data through the scenes
         */
        System.out.println(loginData);
        loginData = null;
        loggedInPanel.getChildren().setAll(pane);
        UserSession.getInstace("").cleanUserSession();
    }

    @FXML
    public void getCatalogView(ActionEvent actionEvent) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../fxmlData/carView.fxml"));
        AnchorPane pane = loader.load();
        /**
         * Set scene and pass data through the scenes
         */
        loggedInPanel.getChildren().setAll(pane);
    }

    @FXML
    public void getBookView(ActionEvent actionEvent) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../fxmlData/bookView.fxml"));
        AnchorPane pane = loader.load();
        /**
         * Set scene and pass data through the scenes
         */
        loggedInPanel.getChildren().setAll(pane);
    }
}