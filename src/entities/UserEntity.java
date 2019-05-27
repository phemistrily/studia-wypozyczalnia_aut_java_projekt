package entities;

import controllers.SqlConnector;
import controllers.SqlInterface;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserEntity implements UserEntityInterface, SqlInterface {

    SqlConnector sqlConnector;
    private String login;
    private String password;
    private String id;
    @FXML
    private TextField loginField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField passwordFieldAgain;
    private String roleId;

    /**
     * konstruktor inicjalizujący połączenie z bazą danych
     */
    public UserEntity() {
        this.sqlConnector = getLocalhostConnection();
    }

    @Override
    public SqlConnector getLocalhostConnection() {
        SqlConnector sqlConnector;
        sqlConnector = new SqlConnector();
        sqlConnector.getConnection("localhost");
        return sqlConnector;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getLogin() {
        return login;
    }

    @Override
    public String getPassword() {
        return password;
    }

    private void setLogin(String login) {
        this.login = login;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void registesrUser(String login, String password) {
        System.out.println(login);
        System.out.println(password);

//        String connectionName = "localhost";
//        ConfigClass configClass = new ConfigClass(connectionName);

//        try {
//            String sql="INSERT INTO users (email, password, active) VALUES ('" + login +"' ,'" + password + "',1)";
//            Connection conn;
//            Statement statement=conn.createStatement();
//            statement.executeUpdate(sql);
//    }
    }

    /**
     * funckja sprawdzająca dane użytkownika wprowadzone podczas logowania z danymi zapisanymi w bazie
     * @param login
     * @param password
     * @return
     * @throws SQLException
     * @throws IOException
     */
    @Override
    public boolean loginUser(String login, String password) throws SQLException, IOException {

        this.setLogin(login);
        this.setPassword(password);
        String query = "SELECT id, email, password FROM users WHERE active = 1 AND email = '" + this.login + "' AND password = '" + this.password + "'";
        //System.out.println(query);
        ResultSet userData = sqlConnector.getData(query);
        userData.last();
        int size = userData.getRow(); //check user exist
        if (size == 0)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Nie poprawny login lub hasło");
            alert.setHeaderText("Nie poprawny login lub hasło");
            alert.setContentText("Nie poprawny login lub hasło");

            alert.showAndWait();
            return false;
        }
        else {
            this.id = userData.getString("id");
            System.out.println("Identyfikator");
            System.out.println(this.id);
            return true;
        }
    }

    /**
     * funckja rejestrująca nowego użytkownika w systemie wraz z walidacją danych wprowadzonych
     * podczas rejestracji
     * @param login
     * @param password
     * @param repeatPassword
     * @return
     * @throws SQLException
     */
    @Override
    public Boolean registerUser(String login, String password, String repeatPassword) throws SQLException {
        this.setLogin(login);
        this.setPassword(password);
        System.out.println("login data:");
        System.out.println(login);
        System.out.println(password);
        System.out.println(repeatPassword);
        if(!password.equals(repeatPassword))
        {
            System.out.println(password);
            System.out.println(repeatPassword);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Hasła nie są takie same");
            alert.setHeaderText("Hasła nie są takie same");
            alert.setContentText("Hasła nie są takie same");

            alert.showAndWait();
            return false;
        }
        if(login.equals(""))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Podaj nazwę użytkownika");
            alert.setHeaderText("Podaj nazwę użytkownika");
            alert.setContentText("Podaj nazwę użytkownika");

            alert.showAndWait();
            return false;
        }
        if(password.equals(""))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Hasło nie może być puste");
            alert.setHeaderText("Hasło nie może być puste");
            alert.setContentText("Hasło nie może być puste");

            alert.showAndWait();
            return false;
        }
        String checkUserExistQuery = "SELECT email FROM users WHERE email = '" + login + "'";
        ResultSet copiedUsersEntity = sqlConnector.getData(checkUserExistQuery);
        copiedUsersEntity.last();
        int size = copiedUsersEntity.getRow();
        System.out.println("userSize");
        System.out.println(size);
        if(size == 0)
        {
            String createUserQuery = "INSERT INTO users (email, password, role, active) VALUES ('" + this.login + "', '" + this.password + "',1,1)";
            sqlConnector.insertData(createUserQuery);
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Zarejestrowano");
            alert.setHeaderText("Zarejestrowano użytkownika " + this.login);
            alert.setContentText("Czy chcesz się zalogować?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                System.out.println("zaloguj");
                return true;
            } else {
                System.out.println("ukryj");
                loginField.clear();
                passwordField.clear();
                passwordFieldAgain.clear();
                return false;
            }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Nie zarejestrowano");
            alert.setHeaderText("Użytkownik istnieje w systemie");
            alert.setContentText("Użytkownik istnieje w systemie");

            alert.showAndWait();
            return false;
        }
    }

    @Override
    public ResultSet getUserData(String userId)
    {
        String query = "SELECT email, role, active FROM users WHERE id = " + userId;
        ResultSet userData = sqlConnector.getData(query);
        this.roleId = this.extractRole(userData);
        return userData;
    }

    @Override
    public String extractRole(ResultSet userData) {
        try {
            userData.next();
            return userData.getString("role");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getRoleId() {
        return roleId;
    }

    @Override
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
