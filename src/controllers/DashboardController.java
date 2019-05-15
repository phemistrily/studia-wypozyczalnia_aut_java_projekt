package controllers;

import entities.UserEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import javax.swing.event.TreeModelEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DashboardController implements Initializable
{
    public TextField loginLabel;
    public PasswordField passwordField;
    @FXML
    private GridPane primaryStage;
    private Stage loggedInStage;
    @FXML
    private Button loginAction;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
    }
    @FXML
    private void loginAction(ActionEvent actionEvent) throws SQLException, IOException {
        String login = loginLabel.getText();
        String password = passwordField.getText();
        UserEntity user = new UserEntity();
        user.loginUser(login, password);

        GridPane pane = FXMLLoader.load(getClass().getResource("../fxmlData/loggedIn.fxml"));
        primaryStage.getChildren().setAll(pane);

        //Stage stage = (Stage) loginAction.getScene().getWindow();

//        Parent root = FXMLLoader.load(getClass().getResource("/fxmlData/loggedIn.fxml"));
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//        stage.show();

//        Scene scene = actionEvent.getSource().getScene();
//        Window window = scene.getWindow();
//        Stage stage = (Stage) window;
        //Parent pane = FXMLLoader.load(getClass().getResource("/fxmlData/loggedIn.fxml"));

    }
}