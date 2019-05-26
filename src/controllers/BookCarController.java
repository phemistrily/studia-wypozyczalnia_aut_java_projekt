package controllers;

import entities.CarsEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import models.UserSession;

import java.io.IOException;
import java.net.URL;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

public class BookCarController implements Initializable {

    private Integer carId;
    @FXML
    private AnchorPane registerFormPanel;
    @FXML
    private Label idInfo;
    @FXML
    private Label nameInfo;
    @FXML
    private Label classInfo;
    @FXML
    private Label brandInfo;
    @FXML
    private Label locationInfo;
    @FXML
    private DatePicker returnDate;
    @FXML
    private DatePicker bookDate;
    @FXML
    private TextField returnCity;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /**
         * get user instance
         */
        bookDate.setValue(LocalDate.now());
        try {
            System.out.println(UserSession.getInstace("").getUserId());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * funkcja odpowiada za ustawianie tekstu wejsciowego dla widoku BookCar
     * @param carData
     * @throws SQLException
     */
    private void setCarView(ResultSet carData) throws SQLException {
        while(carData.next())
        {
            idInfo.setText(carData.getString("lp"));
            nameInfo.setText(carData.getString("name"));
            classInfo.setText(carData.getString("carClass"));
            brandInfo.setText(carData.getString("brand"));
            locationInfo.setText(carData.getString("localisation"));
        }
    }

    /**
     * funckja odpowiada za dokonanie rezerwacji i jej walidację w widoku BookCar
     * @param actionEvent
     * @throws IOException
     * @throws SQLException
     */
    public void bookAction(ActionEvent actionEvent) throws IOException, SQLException {
        LocalDate returnDateValue = returnDate.getValue();
        LocalDate bookDateValue = bookDate.getValue();
        if(bookDateValue != null && returnDateValue != null)
        {
            long date1 = returnDate.getValue().toEpochDay();
            long date2 = bookDate.getValue().toEpochDay();
            int  days  = (int) Math.abs(date1 - date2);
        }
        String city = returnCity.getText();
        if(returnDateValue == null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Podaj datę zwrotu pojazdu");
            alert.setHeaderText("Podaj datę zwrotu pojazdu");
            alert.setContentText("Podaj datę zwrotu pojazdu");

            alert.showAndWait();
        }
        else if (bookDateValue == null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Podaj datę wynajmu pojazdu");
            alert.setHeaderText("Podaj datę wynajmu pojazdu");
            alert.setContentText("Podaj datę wynajmu pojazdu");

            alert.showAndWait();
        }
        else if (city.equals(""))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Podaj miasto, w którym chcesz oddać auto");
            alert.setHeaderText("Podaj miasto, w którym chcesz oddać auto");
            alert.setContentText("Podaj miasto, w którym chcesz oddać auto");

            alert.showAndWait();
        }
        else
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../fxmlData/passReservation.fxml"));
            AnchorPane pane = loader.load();
            /**
             * Set scene and pass data through the scenes
             */
            PassReservationController passsReservationController = loader.getController();
            passsReservationController.initData(returnDateValue,bookDateValue,carId, returnCity.getText());
            registerFormPanel.getChildren().setAll(pane);
        }
        // CarsEntity car = new CarsEntity();
        //car.rentCar()
    }

    /**
     * funkcja przekierowuje do katalogu aut z widoku BookCar
     * @param actionEvent
     * @throws IOException
     */
    public void getCatalogView(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../fxmlData/carView.fxml"));
        AnchorPane pane = loader.load();
        CarViewController carViewController = loader.getController();
        carViewController.initData("bookCar");
        /**
         * Set scene and pass data through the scenes
         */
        registerFormPanel.getChildren().setAll(pane);
    }

    /**
     * funkcja inicjuje pojazd w widoku BookCar
     * @param lp
     */
    public void initCar(int lp) {
        System.out.println("lp: ");
        System.out.println(lp);
        this.carId = lp;
        CarsEntity car = new CarsEntity();
        ResultSet carData = car.getCar(this.carId);
        try {
            this.setCarView(carData);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
