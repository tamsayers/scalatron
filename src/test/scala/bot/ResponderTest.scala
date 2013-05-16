package bot

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.mock.MockitoSugar
import org.scalatest.junit.JUnitRunner
import org.mockito.Mockito._

@RunWith(classOf[JUnitRunner])
class ResponderTest extends FunSuite with MockitoSugar {
  trait TestResponder extends ResponderComponent with MasterResponseComponent with EnvComponent {
    override val masterResponse = mock[MasterResponse]
  }

  test("say text") {
    assert(Say("message").command === "Say(text=message)")
  }

  test("say should not contain illegal characters") {
    List('|', ',', '=', '(').foreach { illegalChar =>
      intercept[IllegalArgumentException] {
        Say(illegalChar.toString)
      }
    }
  }

  test("move should move in the relevant direction") {
    assert(Move(Up).command === "Move(direction=0:-1)")
  }

  test("responder welcome should set welcome variable") {
    new TestResponder {
      val welcomeData = Welcome("", "", 0, 0)

      responder.welcome(welcomeData)

      assert(game === welcomeData)
    }
  }

  test("welcome should say hello") {
    new TestResponder {
      assert(responder.welcome(Welcome("name", "", 0, 0)) === "Say(text=Hello from name)")
    }
  }

  test("master should return the master response") {
    new TestResponder {
      val response = "master response"
      val master = Master(0, "name", 0, "", 0)
      when(masterResponse.getFor(master)).thenReturn(response)

      assert(responder.master(master) === response)
    }
  }
}