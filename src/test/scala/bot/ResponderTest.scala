package bot

import org.junit.runner.RunWith
import org.mockito.Mockito.when
import org.scalatest.FunSuite
import org.scalatest.mock.MockitoSugar
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ResponderTest extends FunSuite with MockitoSugar {
  trait TestResponder extends ResponderComponent with MasterResponseComponent with ViewBuilderComponent with EnvComponent {
    override val masterResponse = mock[MasterResponse]
  }

  trait TestMasterResponse extends MasterResponseComponent with ViewBuilderComponent {
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
      val command = mock[Command]
      val master = Master(0, "name", 0, "", 0)

      when(command.command).thenReturn(response)
      when(masterResponse.getFor(master)).thenReturn(command)

      assert(responder.master(master) === response)
    }
  }

  test("master response should return the best move of the bot") {
    new TestMasterResponse {
      val master = Master(0, "name", 0, "", 0)
      assert(masterResponse.getFor(master) === Move(Up))
    }
  }

  test("master response should move in the current direction if safe") {
    new TestMasterResponse {
      val master = Master(0, "name", 0, "", 0)
      assert(masterResponse.getFor(master) === Move(Up))
    }
  }
}