package shop.user.boundary;

import shop.user.entity.User;

/**
 * Created by zapp on 24/02/15.
 */
public class UserService {

    UserAuthenticator authenticator;

    public User login(String user, String password) {

        if(user == null || user.length() == 0) {
            return null;
        }

        User userToLogin = new User(user, password);
        boolean isValid = authenticator.isUserValid(userToLogin);
        userToLogin.setLoggedIn(isValid);

        return userToLogin;
    }
}
