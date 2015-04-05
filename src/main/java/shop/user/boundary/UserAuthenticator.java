package shop.user.boundary;

import shop.common.DatabaseFacade;
import shop.user.entity.User;

/**
 * Created by zapp on 24/02/15.
 */
public class UserAuthenticator {

    DatabaseFacade databaseFacade;

    public boolean isUserValid(User user) {
        User fetchedUser = (User) databaseFacade.fetch(user);
        return fetchedUser != null;
    }

}
