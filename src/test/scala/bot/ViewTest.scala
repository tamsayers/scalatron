package bot

import org.scalatest.FunSuite
import org.mockito.stubbing.Answer
import org.scalatest.mock.MockitoSugar
import scala.reflect.Manifest
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class ViewTest extends FunSuite with MockitoSugar {
  trait TestViews extends ViewBuilderComponent {
    val cells = """
         _____
        |_b___
        |__M__
        |___W_
        |___W_""".stripMargin.replaceAll("\\s", "")

    val view = viewBuilder.parse(cells)
  }

  test("view should give coordinates for types") {
    new TestViews {
      assert(view.cells === Map((-1, -1) -> Snorg, (0, 0) -> Bot, (1, 1) -> Wall, (2, 1) -> Wall))
    }
  }

  test("is safe should return true if there is not a wall in the given direction") {
    new TestViews {
      assert(view.isSafe(Up))
      assert(view.isSafe(Down + Left))
    }
  }

  test("is safe should return false if there is a wall in the given direction") {
    new TestViews {
      assert(!view.isSafe(Down + Right))
    }
  }
}