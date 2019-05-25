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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.UserSession;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

public class DashboardController implements Initializable
{

    public TextField loginLabel;
    public PasswordField passwordField;


    @FXML
    private Button getRegisterFormBtn;
    @FXML
    private AnchorPane primaryStage;
    @FXML
    private Button loginBtn;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

        //
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
            AnchorPane pane = loader.load();
            /**
             * Set scene and pass data through the scenes
             */
            LoggedInController logggedInController = loader.getController();
            logggedInController.initData(login);
            primaryStage.getChildren().setAll(pane);
            UserSession.dropInstance();
            UserSession.getInstace(user.getId());
            System.out.println("uzytkownik:");
            System.out.println(user.getId());
            //UserSession userSession = new UserSession(user.getId());

        }
    }

    public void getRegisterForm(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../fxmlData/registerForm.fxml"));
        AnchorPane pane = loader.load();
        /**
         * Set scene and pass data through the scenes
         */
        primaryStage.getChildren().setAll(pane);
    }
    @FXML
    void showCarView(ActionEvent event)throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../fxmlData/carView.fxml"));
        AnchorPane pane = loader.load();
        /**
         * Set scene and pass data through the scenes
         */
        primaryStage.getChildren().setAll(pane);
    }

//    private void getLoggedInPanel() throws IOException {
//        AnchorPane pane = FXMLLoader.load(getClass().getResource("../fxmlData/loggedIn.fxml"));
//        primaryStage.getChildren().setAll(pane);
//    }
}