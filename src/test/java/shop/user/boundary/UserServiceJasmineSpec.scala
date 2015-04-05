package shop.user.boundary

import java.util

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import shop.JasmineSpec
import org.scalatest.Matchers
import shop.common.DatabaseFacade
import shop.user.entity.User

@RunWith(classOf[JUnitRunner])
class UserServiceJasmineSpec extends JasmineSpec with Matchers {

  var userService: UserService = _

  describe("A User Service") {

    beforeEach {
      userService = new UserService()
      userService.authenticator = new UserAuthenticator
    }

    describe("Given a user with no user name and password") {

      describe("When the user tries to login with blank user name and password") {
        it("then it should return null") {
          userService.login("", "") shouldBe null
        }
      }

      describe("When the user tries to login without user name and password") {
        it("then it should return null") {
          userService.login(null, null) shouldBe null
        }
      }
    }

    describe("Given a user with a valid user name and password") {

      beforeEach {
        userService.authenticator.databaseFacade = new DatabaseFacade {
          override def fetch(o: scala.Any): AnyRef = {
            val user = o.asInstanceOf[User]
            new User(user.getUsername, user.getPassword)
          }
          override def delete(o: scala.Any): AnyRef = ???
          override def create(o: scala.Any): AnyRef = ???
          override def query(o: scala.Any): util.List[AnyRef] = ???
        }
      }

      describe("When the user have a valid user name and password") {
        it("then it should login the user and return a valid user") {
          val username: String = "userName"
          val password: String = "password"
          val user: User = userService.login(username, password)
          user.getUsername should equal(username)
          user.getPassword should equal(password)
          user.isLoggedIn should be (true)
        }
      }
    }
  }
}
