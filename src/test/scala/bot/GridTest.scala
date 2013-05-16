package bot

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.mock.MockitoSugar
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class GridTest extends FunSuite with MockitoSugar {

  test("direction +") {
    val upAndLeft = Up + Left
    assert(upAndLeft.x === -1)
    assert(upAndLeft.y === -1)
  }
}