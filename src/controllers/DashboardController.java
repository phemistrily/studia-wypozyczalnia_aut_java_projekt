package controllers;

import entities.UserEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

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
        Boolean isLogged = user.loginUser(login, password);
        if (isLogged)
        {
            /**
             * Set new loader
             */
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../fxmlData/loggedIn.fxml"));
            try {
                loader.load();
            } catch (IOException ex) {
                System.out.println("error loading loggedIn");
            }
            /**
             * Set scene and pass data through the scenes
             */
            LoggedInController logggedInController = loader.getController();
            logggedInController.initData(login);
            Parent p = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(p, 1024, 800));
            stage.showAndWait();

            //this.getLoggedInPanel();

        }
    }

//    private void getLoggedInPanel() throws IOException {
//        GridPane pane = FXMLLoader.load(getClass().getResource("../fxmlData/loggedIn.fxml"));
//        primaryStage.getChildren().setAll(pane);
//    }
}