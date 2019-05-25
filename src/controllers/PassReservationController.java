package controllers;

import entities.CarsEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class PassReservationController implements Initializable {

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

    private LocalDate returnDateValue;
    private LocalDate bookDateValue;
    private Integer carId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void initData(LocalDate returnDateValue, LocalDate bookDateValue, Integer carIds) throws SQLException {
        this.returnDateValue = returnDateValue;
        this.bookDateValue = bookDateValue;
        this.carId = carIds;

        System.out.println("init data");
        System.out.println(this.returnDateValue);
        System.out.println(this.bookDateValue);
        System.out.println(this.carId);

        this.reservationFrom.setText("test");
        this.reservationUntil.setText("test2");
        CarsEntity carsEntity = new CarsEntity();
        //String carName = carsEntity.getCarName(carId);
        this.reservationCarName.setText(String.valueOf(carId));
    }

    @FXML
    void backToMain(ActionEvent event) {

    }



}
