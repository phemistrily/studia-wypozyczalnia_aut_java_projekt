package controllers;

import entities.CarsEntity;
import entities.RentsEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class PassReservationController implements Initializable
{

    @FXML
    private AnchorPane passReservationPanel;

    @FXML
    private Label reservationFrom;

    @FXML
    private Label reservationUntil;

    @FXML
    private Label reservationCarName;

    @FXML
    private Button mainButton;

    @FXML
    private Label reservationPrice;

    private LocalDate returnDateValue;
    private LocalDate bookDateValue;
    private Integer carId;
    private String returnCity;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {

    }

    /**
     * funkcja inicjująca w widoku PassReservation szczegóły wstawiając je w odpowiednie etykiety
     * do wyświetlenia - Label
     * @param returnDateValue
     * @param bookDateValue
     * @param carIds
     * @param returnCity
     * @throws SQLException
     */
    public void initData(LocalDate returnDateValue, LocalDate bookDateValue, Integer carIds, String returnCity) throws SQLException
    {
        this.returnDateValue = returnDateValue;
        this.bookDateValue = bookDateValue;
        this.carId = carIds;
        this.returnCity = returnCity;

        System.out.println("init data");
        System.out.println(this.returnDateValue);
        System.out.println(this.bookDateValue);
        System.out.println(this.carId);


        CarsEntity carsEntity = new CarsEntity();
        RentsEntity rentsEntity = new RentsEntity();

        String carName = carsEntity.getCarName(carId);
        String dailypricePerRent = carsEntity.getPriceForRent(carId);
        long date1 = returnDateValue.toEpochDay();
        long date2 = bookDateValue.toEpochDay();
        int  days  = (int) Math.abs(date1 - date2);
        int pricePerRent = Integer.valueOf(dailypricePerRent) * days;


        this.reservationFrom.setText(String.valueOf(this.bookDateValue));
        this.reservationUntil.setText(String.valueOf(this.returnDateValue));
        this.reservationCarName.setText(carName);
        this.reservationPrice.setText(String.valueOf(pricePerRent));

        rentsEntity.createRent(String.valueOf(this.bookDateValue), String.valueOf(this.returnDateValue), carId, returnCity);
    }

    /**
     * funkjcja przechodząca do głównej strony aplikacji z widoku PassReservation
     * @param event
     * @throws IOException
     */
    @FXML
    void backToMain(ActionEvent event)throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../fxmlData/main.fxml"));
        AnchorPane pane = loader.load();
        /**
         * Set scene and pass data through the scenes
         */
        passReservationPanel.getChildren().setAll(pane);
    }

    /**
     * funckja przechodząca do strony konta użytkownika z widoku PassReservation
     * @param event
     * @throws IOException
     */
    @FXML
    void goToAccount(ActionEvent event)throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../fxmlData/loggedIn.fxml"));
        AnchorPane pane = loader.load();
        /**
         * Set scene and pass data through the scenes
         */
        passReservationPanel.getChildren().setAll(pane);
    }


}
