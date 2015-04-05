package shop.user.boundary;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import shop.common.DatabaseFacade;
import shop.user.entity.User;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(HierarchicalContextRunner.class)
public class UserServiceTest {

    private UserService userService;

    @Before
    public void setUp() throws Exception {
        userService = new UserService();
        userService.authenticator = new UserAuthenticator();
    }

    @Test
    public void whenUserDoNotHaveALogin_thenUserIsNull() {
        User user = userService.login(null, null);
        assertNull(user);
    }

    public class ValidUsers {

        @Before
        public void setup() {
            userService.authenticator.databaseFacade = new DatabaseFacade() {

                public Object fetch(Object o) {
                    User user = (User) o;
                    return new User(user.getUsername(), user.getPassword());
                }

                public Object create(Object o) {
                    return null;
                }

                public Object delete(Object o) {
                    return null;
                }

                public List<Object> query(Object o) {
                    return null;
                }
            };
        }

        @Test
        public void whenUserHaveAUsernameAndPassword_thenAValidUserIsReturned() {
            User user = userService.login("user", "password");
            assertEquals("user", user.getUsername());
            assertEquals(true, user.isLoggedIn());
        }
    }


    public class InvalidUsers {

        @Before
        public void setup() {
            userService.authenticator.databaseFacade = new DatabaseFacade() {

                public Object fetch(Object o) {
                    return null;
                }

                public Object create(Object o) {
                    return null;
                }

                public Object delete(Object o) {
                    return null;
                }

                public List<Object> query(Object o) {
                    return null;
                }
            };
        }

        @Test
        public void whenUserHaveOnlyUsernameAndNoPassword_thenTheReturnedUserIsNOTLoggedIn() {
            User user = userService.login("user", null);
            assertEquals("user", user.getUsername());
            assertEquals(false, user.isLoggedIn());
        }

    }

}
