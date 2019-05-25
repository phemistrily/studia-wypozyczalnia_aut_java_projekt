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

    /**
     * funckja dodająca samochód do bazy w widocu AddCar
     * @param event
     */
    @FXML
    void addCarToDb(ActionEvent event) {

    }

    /**
     * funckjac przechodząca wstecz w widoku addCar
     * @param event
     */
    @FXML
    void backAction(ActionEvent event) {

    }

    /**
     * funkcja przechodząca do strony głównej aplikacji z widoku addCar
     * @param event
     */
    @FXML
    void backToMain(ActionEvent event) {

    }

    /**
     * funckcja przechodząca do katalogu aut z widoku AddCar
     * @param event
     */
    @FXML
    void getCatalogView(ActionEvent event) {

    }
    @FXML
    void initialize() {


    }

    /**
     * funckja edytująca dane samochodu w bazie w widoku AddCar
     * @param event
     */
    public void editCarInDb(ActionEvent event)
    {
    }
}
