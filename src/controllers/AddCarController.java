package controllers;

import entities.UserEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddCarController  {

    @FXML
    private AnchorPane addCarPanel;

    @FXML
    private TextField idLabel;

    @FXML
    private TextField nameLabel;

    @FXML
    private TextField classLabel;

    @FXML
    private TextField brandLabel;

    @FXML
    private TextField localisationLabel;

    @FXML
    void addCarToDb(ActionEvent event) {

    }

    @FXML
    void backAction(ActionEvent event) {

    }

    @FXML
    void backToMain(ActionEvent event) {

    }

    @FXML
    void getCatalogView(ActionEvent event) {

    }
    @FXML
    void initialize() {


    }

    public void editCarInDb(ActionEvent event)
    {
    }
}
