package models;


public final class UserSession {

    private static UserSession instance;

    private String userId;

    public UserSession(String userId) {
        this.userId = userId;
    }

    public static UserSession getInstace(String userName) {
        if(instance == null) {
            instance = new UserSession(userName);
        }
        return instance;
    }

    public String getUserId() {
        return userId;
    }

    public void cleanUserSession() {
        userId = "";// or null
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "userName='" + userId + '\'' +
                '}';
    }
}
