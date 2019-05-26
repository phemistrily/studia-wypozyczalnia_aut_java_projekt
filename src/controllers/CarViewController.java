package controllers;

import entities.CarsEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import models.CarsTableModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import entities.UserEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import models.UserSession;


import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class CarViewController implements Initializable {

    @FXML
    private TextField localisationBTN;
    @FXML
    private TextField ByClassBTN;
    @FXML
    public Button bookCarBtn;
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
    @FXML
    private TableColumn<CarsTableModel, Integer> price_per_day;

    private ObservableList<CarsTableModel> carsTableModel = FXCollections.observableArrayList();
    @FXML
    private Button editButton;
    @FXML
    private Button addButton;

    private String lastLocation;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            System.out.println("userInitCarView");
            System.out.println(UserSession.getInstace("").getUserId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(UserSession.getInstace("").getUserId().equals(""))
            {
                bookCarBtn.setVisible(false);
                editButton.setVisible(false);
                System.out.println("dziala");
                System.out.println(UserSession.getInstace("").getUserRole());
            }
            else if(!UserSession.getInstace("").getUserRole().equals("2"))
            {
                System.out.println("userdata");
                System.out.println(UserSession.getInstace(""));
                System.out.println(UserSession.getInstace("").getUserRole());
                editButton.setVisible(false);
                addButton.setVisible(false);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

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

    public void initData(String lastLocation)
    {
        this.lastLocation = lastLocation;
    }

    FilteredList filter = new FilteredList(carsTableModel, e -> true);

    private void initializeFactory() {
        lp.setCellValueFactory(new PropertyValueFactory<>("lp"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        carClass.setCellValueFactory(new PropertyValueFactory<>("carClass"));
        brand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        isRented.setCellValueFactory(new PropertyValueFactory<>("isRented"));
        localisation.setCellValueFactory(new PropertyValueFactory<>("localisation"));
        price_per_day.setCellValueFactory(new PropertyValueFactory<>("price_per_day"));
    }


    private void getCars() throws IOException, SQLException {
        CarsEntity cars = new CarsEntity();
        ResultSet carsData = cars.getCars();
        this.putCarsToCarsTableModel(carsData);
    }

    private void putCarsToCarsTableModel(ResultSet carsData) throws SQLException {
        while (carsData.next()) {
            carsTableModel.add(new CarsTableModel(carsData.getInt("lp"), carsData.getString("name"), carsData.getString("carClass"),
                    carsData.getString("brand"), carsData.getString("is_rented"), carsData.getString("localisation"), carsData.getInt("price_per_day")));
        }
    }


    @FXML
    void backStepButton(ActionEvent event) {

    }

    @FXML
    void mainAppView(ActionEvent event) {

    }


    public void bookCar(ActionEvent event) throws IOException, SQLException {
        if (UserSession.getInstace("").getUserId().equals("") || UserSession.getInstace("").getUserId().equals(null)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Aby zamówić auto musisz się zalogować");
            alert.setHeaderText("Aby zamówić auto musisz się zalogować");
            alert.setContentText("Aby zamówić auto musisz się zalogować");

            alert.showAndWait();
        } else {
            CarsTableModel car = carCatalogTableView.getSelectionModel().getSelectedItem();//check if not selected
            Integer id = car.getLp();
            if (id == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Wybierz auto");
                alert.setHeaderText("Wybierz auto");
                alert.setContentText("Wybierz auto");

                alert.showAndWait();
            } else {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("../fxmlData/bookCar.fxml"));
                AnchorPane pane = loader.load();
                BookCarController bookCarController = loader.getController();
                bookCarController.initCar(id);
                /**
                 * Set scene and pass data through the scenes
                 */
                carView.getChildren().setAll(pane);
            }
        }
    }

    public void backToMain(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../fxmlData/main.fxml"));
        AnchorPane pane = loader.load();
        /**
         * Set scene and pass data through the scenes
         */
        carView.getChildren().setAll(pane);
    }

    public void backAction(ActionEvent event) throws IOException {
        //carView.getScene().getWindow().hide(); //zamkniecie aplikacji
        FXMLLoader loader = new FXMLLoader();
        String fxmlControllerData;
        if (!lastLocation.isEmpty())
        {
            fxmlControllerData = "../fxmlData/" + this.lastLocation + ".fxml";
        }
        else
        {
            fxmlControllerData = "../fxmlData/main.fxml";
        }
        loader.setLocation(getClass().getResource(fxmlControllerData));
        AnchorPane pane = loader.load();
        /**
         * Set scene and pass data through the scenes
         */
        carView.getChildren().setAll(pane);
    }

    public void filtrByLocalisation(javafx.scene.input.KeyEvent keyEvent) {
        localisationBTN.textProperty().addListener((observable, oldValue, newValue) -> {
            filter.setPredicate((Predicate<? super CarsTableModel>) (CarsTableModel ctb) -> {
                if (newValue.isEmpty() || newValue == null) {
                    return true;
                } else if (ctb.getLocalisation().contains(newValue)) {
                    return true;
                }
                return false;
            });
        });
        SortedList sort = new SortedList(filter);
        sort.comparatorProperty().bind(carCatalogTableView.comparatorProperty());
        carCatalogTableView.setItems(sort);
    }


    public void filtrByClass(KeyEvent keyEvent) {
        ByClassBTN.textProperty().addListener((observable, oldValue, newValue) -> {
            filter.setPredicate((Predicate<? super CarsTableModel>) (CarsTableModel ctb) -> {
                if (newValue.isEmpty() || newValue == null) {
                    return true;
                } else if (ctb.getCarClass().contains(newValue)) {
                    return true;
                }
                return false;
            });
        });
        SortedList sort = new SortedList(filter);
        sort.comparatorProperty().bind(carCatalogTableView.comparatorProperty());
        carCatalogTableView.setItems(sort);
    }

    public void editAction(ActionEvent event) throws IOException, SQLException {
        if (UserSession.getInstace("").getUserId().equals("") || !UserSession.getInstace("").getUserRole().equals("2")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Aby edytować auto musisz być administratorem");
            alert.setHeaderText("Aby edytować auto musisz być administratorem");
            alert.setContentText("Aby edytować auto musisz być administratorem");

            alert.showAndWait();
        } else {
            CarsTableModel car = carCatalogTableView.getSelectionModel().getSelectedItem();
            System.out.println(car.getLp());
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../fxmlData/editCar.fxml"));
            AnchorPane pane = loader.load();
            EditCarController editCarController = loader.getController();
            editCarController.initCar(car.getLp());
            /**
             * Set scene and pass data through the scenes
             */
            carView.getChildren().setAll(pane);
        }
    }

    public void addAction(ActionEvent actionEvent) {
        //
    }
}
