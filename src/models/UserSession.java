package models;


import entities.UserEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class UserSession {

    private static UserSession instance;

    private String userId;
    private String userRole;

    public UserSession(String userId) {
        this.userId = userId;
        if(!userId.isEmpty())
        {
            UserEntity user = new UserEntity();
            ResultSet userData = user.getUserData(userId);
            String role = user.getRoleId();
            this.userRole = role;
        }
    }

    public static UserSession getInstace(String userName) throws SQLException {
        if(instance == null) {
            instance = new UserSession(userName);
        }
        return instance;
    }

    public static void dropInstance() {
        instance = null;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserRole() {
        return userRole;
    }

    public void cleanUserSession() {
        userId = "";// or null
        userRole = "";
    }

    public void setUserId(String thisUserId) {
        userId = thisUserId;
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "userName='" + userId + '\'' +
                ",userRole='" + userRole + '\'' +
                '}';
    }
}
