package bot

import org.mockito.Mockito._
import org.junit.Test
import env.TestEnvironment

class BotSpec extends TestEnvironment {
  val masterMessage = "React(entity=Master,time=0,view=_W_W_????,energy=1000)"
  val spawnMessage = "React(entity=Spawn,time=0,view=_W_W_????,energy=1000,dx=10,dy=-10)"
  override val bot = new Bot

  @Test def respondShouldBeTheMasterResponseMessage { 
    val message = "message"
      
    when(masterResponse.process("_W_W_????", 1000)).thenReturn(message)
    
    bot.respond(masterMessage) should equal (message)
  }
 
  @Test def respondShouldReturnAnEmptyStringForNoMatch() {   
    bot.respond("NoMatch()") should equal ("")
  }
}
