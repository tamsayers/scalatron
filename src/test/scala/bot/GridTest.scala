package bot

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.mock.MockitoSugar
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class GridTest extends FunSuite with MockitoSugar {

  test("direction +") {
    val upAndLeft = Up + Left
    assert(upAndLeft.toPosition === (-1, -1))
  }

  test("direction reverse") {
    println(Up.reverse)
    assert(Up.reverse.toPosition === Down.toPosition)
  }

  test("direction positions") {
    assert(Up.toPosition === (0, -1))
    assert(Down.toPosition === (0, 1))
    assert(Left.toPosition === (-1, 0))
    assert(Right.toPosition === (1, 0))
  }
}