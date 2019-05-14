import controllers.SqlConnector;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import controllers.ConfigClass;
import javafx.scene.control.Button;

import java.sql.SQLException;
import java.sql.Statement;

public class Main extends Application implements EventHandler<ActionEvent> {

    private Button loginBtn;
    public ConfigClass configClass;

    @Override
    public void start(Stage primaryStage) throws Exception{
        String connectionName = "localhost";
        configClass = new ConfigClass(connectionName);
        Parent root = FXMLLoader.load(getClass().getResource("/fxmlData/main.fxml"));
        primaryStage.setTitle(configClass.mainTitle);

        primaryStage.setScene(new Scene(root, 1024, 800));
        primaryStage.show();
    }

    @Override
    public void handle(ActionEvent event)
    {
        if(event.getSource() == loginBtn)
        {
            System.out.println("dziala");
        }
    }


    public static void main(String[] args) throws SQLException {
        launch(args);
        System.out.println("program closed");
//przykładowy sql
//        String sql="INSERT INTO users (email, password, active) VALUES ('test','test',1)";
//        Statement statement=conn.createStatement();
//        statement.executeUpdate(sql);

    }
}
