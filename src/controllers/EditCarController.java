package controllers;

import entities.CarsEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EditCarController implements Initializable {

    private int carId;
    @FXML
    private AnchorPane editCarPanel;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

        //
    }

    public void getCatalogView(ActionEvent actionEvent) {
    }

    public void backAction(ActionEvent actionEvent) {
    }

    public void backToMain(ActionEvent actionEvent) throws IOException

    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../fxmlData/main.fxml"));
        AnchorPane pane = loader.load();
        /**
         * Set scene and pass data through the scenes
         */
        editCarPanel.getChildren().setAll(pane);
    }

    public void editCarInDb(ActionEvent actionEvent) {
    }

    public void initCar(int lp) {
        this.carId = lp;
        CarsEntity car = new CarsEntity();
        ResultSet carData = car.getCar(this.carId);
        try {
            this.setCarView(carData);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void setCarView(ResultSet carData) throws SQLException {
        while(carData.next())
        {
            idLabel.setText(carData.getString("lp"));
            nameLabel.setText(carData.getString("name"));
            classLabel.setText(carData.getString("carClass"));
            brandLabel.setText(carData.getString("brand"));
            localisationLabel.setText(carData.getString("localisation"));
        }
    }
}
