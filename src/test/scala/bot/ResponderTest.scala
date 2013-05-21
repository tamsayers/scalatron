package bot

import org.junit.runner.RunWith
import org.mockito.Mockito.when
import org.scalatest.FunSuite
import org.scalatest.mock.MockitoSugar
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ResponderTest extends FunSuite with MockitoSugar {
  trait TestResponderBase extends MasterResponseComponent with ViewBuilderComponent with EnvComponent {
    val master = Master(0, "name", 0, "____M____", 0)
  }

  trait TestResponder extends TestResponderBase with ResponderComponent {
    override val masterResponse = mock[MasterResponse]
  }

  trait TestMasterResponse extends TestResponderBase {
    override val viewBuilder = mock[ViewBuilder]
  }

  test("say text") {
    assert(Say("message").command === "Say(text=message)")
  }

  test("say text should not be longer than 10 chars") {
    intercept[IllegalArgumentException] {
      Say("message is too long")
    }
  }

  test("say should not contain illegal characters") {
    List('|', ',', '=', '(').foreach { illegalChar =>
      intercept[IllegalArgumentException] {
        Say(illegalChar.toString)
      }
    }
  }

  test("move should move in the relevant direction") {
    new Grid {
      assert(Move(Up).command === "Move(direction=0:-1)")
    }
  }

  test("responder welcome should set welcome variable") {
    new TestResponder {
      val welcomeData = Welcome("", "", 0, 0)

      responder.welcome(welcomeData)

      assert(game === welcomeData)
    }
  }

  test("welcome should say name") {
    new TestResponder {
      assert(responder.welcome(Welcome("name", "", 0, 0)) === "Say(text=name)")
    }
  }

  test("master should return the master response") {
    new TestResponder {
      val response = "master response"
      val command = mock[Command]

      when(command.command).thenReturn(response)
      when(masterResponse.getFor(master)).thenReturn(command)

      assert(responder.master(master) === response)
    }
  }

  test("master response should move in the current direction if safe") {
    new TestMasterResponse {
      val view = mock[View]
      when(viewBuilder.parse(master.view)).thenReturn(view)
      when(view.isSafe(currentDirection)).thenReturn(true)

      assert(masterResponse.getFor(master) === Move(currentDirection))
    }
  }

  test("master response should reverse if the current direction is not safe") {
    new TestMasterResponse {
      val view = mock[View]
      when(viewBuilder.parse(master.view)).thenReturn(view)
      when(view.isSafe(currentDirection)).thenReturn(false)
      val previousDirection = currentDirection

      assert(masterResponse.getFor(master) === Move(previousDirection.reverse))
    }
  }

  test("master response should keep track of the current direction") {
    new TestMasterResponse {
      val view = mock[View]
      when(viewBuilder.parse(master.view)).thenReturn(view)
      when(view.isSafe(Up)).thenReturn(false)
      val previousDirection = currentDirection
      masterResponse.getFor(master)

      assert(currentDirection === previousDirection.reverse)
    }
  }
}