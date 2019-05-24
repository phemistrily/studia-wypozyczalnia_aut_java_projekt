package controllers;

import entities.CarsEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import models.UserSession;

import java.net.URL;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

public class BookCarController implements Initializable {

    private Integer carId;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /**
         * get user instance
         */
        System.out.println(UserSession.getInstace("").getUserId());

    }

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

    public void bookAction(ActionEvent actionEvent) {
    }

    public void getCatalogView(ActionEvent actionEvent) {
    }

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
