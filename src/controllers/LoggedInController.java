package controllers;

import entities.UserEntity;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class LoggedInController implements Initializable {

    private String test;
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
}
