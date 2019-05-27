package controllers;

import entities.CarsEntity;
import entities.RentsEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import models.BookTableModel;
import models.CarsTableModel;

import javax.accessibility.AccessibleComponent;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class BookViewController implements Initializable {

    @FXML
    private TextField ByRentCityBTN;
    @FXML
    private TextField ByReturnCityBTN;
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

    FilteredList filter = new FilteredList(bookTableModel, e -> true);
    @FXML
    private Button editButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        editButton.setVisible(false);
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

    /**
     * funckja pobierająca listę zarezerwowanych pojazdów
     * @throws IOException
     * @throws SQLException
     */
    private void getBooks() throws IOException, SQLException {
        RentsEntity books = new RentsEntity();
        ResultSet booksData = books.getRents();
        this.putBooksToBookTableModel(booksData);
    }

    /**
     * funckja ustawiająca widok listy zarezerwowanych pojazdów
     * @param booksData
     * @throws SQLException
     */
    private void putBooksToBookTableModel(ResultSet booksData) throws SQLException {
        while (booksData.next()){
            bookTableModel.add(new BookTableModel(booksData.getInt("lp"), booksData.getString("name"), booksData.getString("start_date"),
                    booksData.getString("end_date"), booksData.getString("start_city"), booksData.getString("end_city")));
        }
    }

    /**
     * funckja przechodząca wstecz z widoku BookView
     * @param actionEvent
     */
    public void backAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../fxmlData/loggedIn.fxml"));
        AnchorPane pane = loader.load();
        /**
         * Set scene and pass data through the scenes
         */
        carView.getChildren().setAll(pane);
    }

    /**
     * funckcja przchodząca do strony głównej aplikacji z widoku BookView
     * @param actionEvent
     * @throws IOException
     */
    public void backToMain(ActionEvent actionEvent) throws  IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../fxmlData/main.fxml"));
        AnchorPane pane = loader.load();
        /**
         * Set scene and pass data through the scenes
         */
        carView.getChildren().setAll(pane);
    }

    public void filterByDataFrom(KeyEvent keyEvent)
    {
    }

    public void filterByDataUntil(KeyEvent keyEvent)
    {
    }

    /**
     * funkcja obsługująca edycję pojazdu w widoku BookView
     * @param event
     */
    public void editAction(ActionEvent event)
    {
    }

    public void filterByLocalisation(KeyEvent keyEvent)
    {

    }

    public void filterByClass(KeyEvent keyEvent)
    {

    }

    /**
     * funckja filtrująca listę pojazdów w widoku BookView
     * @param keyEvent
     */
    public void filterByCityOfRent(KeyEvent keyEvent) {
        ByRentCityBTN.textProperty().addListener((observable, oldValue, newValue) -> {
            filter.setPredicate((Predicate<? super BookTableModel>) (BookTableModel ctb) -> {
                if (newValue.isEmpty() || newValue == null) {
                    return true;
                } else if (ctb.getStart_city().contains(newValue)) {
                    return true;
                }
                return false;
            });
        });
        SortedList sort = new SortedList(filter);
        sort.comparatorProperty().bind(bookCatalogTableView.comparatorProperty());
        bookCatalogTableView.setItems(sort);
    }

    public void filterByCityOfReturn(KeyEvent keyEvent) {
        ByReturnCityBTN.textProperty().addListener((observable, oldValue, newValue) -> {
            filter.setPredicate((Predicate<? super BookTableModel>) (BookTableModel ctb) -> {
                if (newValue.isEmpty() || newValue == null) {
                    return true;
                } else if (ctb.getEnd_city().contains(newValue)) {
                    return true;
                }
                return false;
            });
        });
        SortedList sort = new SortedList(filter);
        sort.comparatorProperty().bind(bookCatalogTableView.comparatorProperty());
        bookCatalogTableView.setItems(sort);
    }
}
