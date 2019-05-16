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

    private String test;
    @FXML
    private Button loggedInLogOut;
    @FXML
    private Label loggedInWelcomeLabel;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
    }

    public void initData(String login) {
        this.test = login;
        this.loggedInWelcomeLabel.setText("Witaj " + this.test);
    }

    public void logoutAction(javafx.event.ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../fxmlData/main.fxml"));
        try {
            loader.load();
        } catch (IOException ex) {
            System.out.println("error loading main");
        }
        Parent p = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(p, 1024, 800));
        stage.showAndWait();
    }
}
