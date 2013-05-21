package bot

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.mock.MockitoSugar
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class GridTest extends FunSuite with MockitoSugar {

  trait TestGrid extends Grid

  test("direction +") {
    new TestGrid {
      val upAndLeft = Up + Left
      assert(upAndLeft === Position(-1, -1))
    }
  }

  test("direction reverse") {
    new TestGrid {
      println(Up.reverse)
      assert(Up.reverse === Down)
    }
  }

  test("direction positions") {
    new TestGrid {
      assert(Up === Position(0, -1))
      assert(Down === Position(0, 1))
      assert(Left === Position(-1, 0))
      assert(Right === Position(1, 0))
    }
  }
}