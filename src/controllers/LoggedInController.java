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
        UserEntity user = new UserEntity();
        System.out.println("dupa");
        System.out.println(this.test);
        loggedInWelcomeLabel.setText("." + this.test);
    }

    public void initData(String login) {
        this.test = login;
        System.out.println("test login");
        System.out.println(login);
        System.out.println(this.test);
    }
}
