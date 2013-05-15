package bot

import org.scalatest.FunSuite
import org.mockito.stubbing.Answer
import org.scalatest.mock.MockitoSugar
import scala.reflect.Manifest
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ViewTest extends FunSuite with MockitoSugar {
  trait TestViews extends GameView {
    val cells =
      """_____
        |_b___
        |__M__
        |___W_
        |___W_""".stripMargin
  }

  test("view should give coordinates for types") {
    new TestViews {
      assert(ViewBuilder.parse(cells) === new View(Map((-1,-1) -> Snorg, (1,1) -> Wall, (2,1) -> Wall)))
    }
  }
}