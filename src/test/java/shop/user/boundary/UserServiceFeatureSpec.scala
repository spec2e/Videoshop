import org.junit.runner.RunWith
import org.scalatest.{FeatureSpec, GivenWhenThen}
import org.scalatest.junit.JUnitRunner
import shop.user.boundary.UserService

@RunWith(classOf[JUnitRunner])
class UserServiceFeatureSpec extends FeatureSpec with GivenWhenThen {

  info("As a user")
  info("I want to be able to login")
  info("So I can use the Video shop")

  feature("Login") {
    scenario("User tries to login without having user name and password") {
      Given("a user tries to login")
      val userService = new UserService();

      When("the user tries to login without user name and password")
      val user = userService.login("", "")

      Then("user is null")
      assert(user == null)
    }
  }

}