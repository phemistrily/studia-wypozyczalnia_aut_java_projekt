package controllers;

import entities.CarsEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import models.CarsTableModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class carViewController implements Initializable
{

    @FXML
    private AnchorPane carView;
    @FXML
    private TableView<CarsTableModel> carCatalogTableView;
    @FXML
    private TableColumn<CarsTableModel, Integer> lp;
    @FXML
    private TableColumn<CarsTableModel, String> name;
    @FXML
    private TableColumn<CarsTableModel, String> carClass;
    @FXML
    private TableColumn<CarsTableModel, String> brand;
    @FXML
    private TableColumn<CarsTableModel, String> isRented;
    @FXML
    private TableColumn<CarsTableModel, String> localisation;

    private ObservableList<CarsTableModel> carsTableModel = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //make sure the property value factory should be exactly same as the e.g getStudentId from your model class

        //add your data to the table here.
        this.initializeFactory();
        //this.getCars();
        carCatalogTableView.setItems(carsTableModel);
    }

    private void initializeFactory() {
        lp.setCellValueFactory(new PropertyValueFactory<>("lp"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        carClass.setCellValueFactory(new PropertyValueFactory<>("carClass"));
        brand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        isRented.setCellValueFactory(new PropertyValueFactory<>("isRented"));
        localisation.setCellValueFactory(new PropertyValueFactory<>("localisation"));
    }


    private void getCars() throws IOException, SQLException {
        CarsEntity cars = new CarsEntity();
        ResultSet carsData = cars.getCars();
    }


    @FXML
    void backStepButton(ActionEvent event)
    {

    }

    @FXML
    void mainAppView(ActionEvent event)
    {

    }


}
