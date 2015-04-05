package shop.user.entity;

/**
 * Created by zapp on 24/02/15.
 */
public class User {

    private String username;
    private String password;
    private boolean isLoggedIn;

    public User(String user, String password) {
        this.username = user;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }
}
