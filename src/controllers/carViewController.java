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
        this.initializeFactory();
        try {
            this.getCars();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        this.putCarsToCarsTableModel(carsData);
    }

    private void putCarsToCarsTableModel(ResultSet carsData) throws SQLException {
        while (carsData.next()){
            carsTableModel.add(new CarsTableModel(carsData.getInt("lp"), carsData.getString("name"), carsData.getString("carClass"),
                    carsData.getString("brand"), carsData.getString("is_rented"), carsData.getString("localisation")));
        }
    }


    @FXML
    void backStepButton(ActionEvent event)
    {

    }

    @FXML
    void mainAppView(ActionEvent event)
    {

    }


    public void bookCar(ActionEvent event)
    {
    }

    public void backToMain(ActionEvent event)
    {
    }

    public void backAction(ActionEvent event)
    {
    }
}
