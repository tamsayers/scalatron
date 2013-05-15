package bot

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner
import org.scalatest.mock.MockitoSugar
import org.mockito.Mockito._

@RunWith(classOf[JUnitRunner])
class BotTest extends FunSuite with MockitoSugar {
  trait Inputs extends ControlFunctionComponent with ResponderComponent with EnvComponent {
    override val responder = mock[Responder]
    val respond = controlFunction.respond _

    val welcomeInput = "Welcome(name=Bot Test,path=/file/path,apocalypse=500,round=1)"
    val masterBotInput = "React(generation=0,name=name,time=100,view=__W_W_W__,energy=100)"

    val response = "Move(Say(text=Hello from Bot Test))"
  }

  test("welcome bot") {
    new Inputs {
      when(responder.welcome(Welcome("Bot Test", "/file/path", 500, 1))).thenReturn(response)

      assert(respond(welcomeInput) === response)
    }
  }

  test("react to master bot") {
    new Inputs {
      val master = Master(0, "name", 100, "__W_W_W__", 100)
      when(responder.master(master)).thenReturn(response)

      assert(respond(masterBotInput) === response)
    }
  }
}