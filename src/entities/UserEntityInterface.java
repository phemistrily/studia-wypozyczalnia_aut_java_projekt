package entities;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface UserEntityInterface {
    String getId();

    void setId(String id);

    String getLogin();

    String getPassword();

    void registesrUser(String login, String password);

    boolean loginUser(String login, String password) throws SQLException, IOException;

    Boolean registerUser(String login, String password, String repeatPassword) throws SQLException;

    ResultSet getUserData(String userId);

    String extractRole(ResultSet userData);

    String getRoleId();

    void setRoleId(String roleId);
}
