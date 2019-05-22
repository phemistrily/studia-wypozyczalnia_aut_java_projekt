package controllers;

import entities.CarsEntity;
import entities.RentsEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import models.BookTableModel;
import models.CarsTableModel;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class bookViewController implements Initializable {

    @FXML
    private TableView<BookTableModel> bookCatalogTableView;
    @FXML
    private AnchorPane carView;
    @FXML
    private TableColumn<BookTableModel, Integer> lp;
    @FXML
    private TableColumn<BookTableModel, String> name;
    @FXML
    private TableColumn<BookTableModel, String> start_date;
    @FXML
    private TableColumn<BookTableModel, String> end_date;
    @FXML
    private TableColumn<BookTableModel, String> start_city;
    @FXML
    private TableColumn<BookTableModel, String> end_city;

    private ObservableList<BookTableModel> bookTableModel = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.initializeFactory();
        try {
            this.getBooks();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        bookCatalogTableView.setItems(bookTableModel);
    }

    private void initializeFactory() {
        lp.setCellValueFactory(new PropertyValueFactory<>("lp"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        start_date.setCellValueFactory(new PropertyValueFactory<>("start_date"));
        end_date.setCellValueFactory(new PropertyValueFactory<>("end_date"));
        start_city.setCellValueFactory(new PropertyValueFactory<>("start_city"));
        end_city.setCellValueFactory(new PropertyValueFactory<>("end_city"));
    }

    private void getBooks() throws IOException, SQLException {
        RentsEntity books = new RentsEntity();
        ResultSet booksData = books.getRents();
        this.putBooksToBookTableModel(booksData);
    }

    private void putBooksToBookTableModel(ResultSet booksData) throws SQLException {
        while (booksData.next()){
            bookTableModel.add(new BookTableModel(booksData.getInt("lp"), booksData.getString("name"), booksData.getString("start_date"),
                    booksData.getString("end_date"), booksData.getString("start_city"), booksData.getString("end_city")));
        }
    }

    public void backAction(ActionEvent actionEvent) {
    }

    public void backToMain(ActionEvent actionEvent) throws  IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../fxmlData/main.fxml"));
        AnchorPane pane = loader.load();
        /**
         * Set scene and pass data through the scenes
         */
        carView.getChildren().setAll(pane);
    }
}
