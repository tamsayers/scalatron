package bot.cell

import org.specs2.mutable.Specification
import org.specs2.mutable.SpecificationWithJUnit
import bot.cell._

class CellSpec extends SpecificationWithJUnit {
  case class TestCell(position: (Int, Int)) extends Uninteresting
  case class GoodCell(position: (Int, Int), energy: Int = 100) extends Food
  case class BadCell(position: (Int, Int), energy: Int = -100, range: Int = 1) extends Bad

  "the distance to the cell" should {
    "be the number of moves to the cell" in {
      TestCell(2, 5).distance must be equalTo 5
    }
    "be a positive integer" in {
      TestCell(2, -5).distance must be equalTo 5
    }
  }

  "the next move towards a cell" should {
    "be right and up" in {
      TestCell(2, 5).direction must be equalTo Direction(1, -1)
    }
    "be left and up" in {
      TestCell(-1, 1).direction must be equalTo Direction(-1, -1)
    }
    "be down" in {
      TestCell(0, -5).direction must be equalTo Direction(0, 1)
    }
  }

  "a good cell" should {
    "have a value of its energy if it is in the next cell" in {
      GoodCell((1, 1)).value must be equalTo 100
    }
    "have a value of half its energy if it is 2 cells away" in {
      GoodCell((1, 2)).value must be equalTo 100 / 2
    }
  }
  "a bad cell" should {
    "have a value of it's energy if it is at range" in {
      BadCell((1, 1)).value must be equalTo -100
    }
    "have a value of it's energy if it is in range" in {
      BadCell((1, 2), -100, 3).value must be equalTo -100
    }
    "have no value if it is out of range" in {
      BadCell((2, 4), -100, 2).value must be equalTo 0
    }
  }
  
  "the next move towards a bad cell" should {
    "be right and up" in {
      BadCell((1, 2)).direction must be equalTo Direction(1, -1)
    }
    "be right and down" in {
      BadCell((2, -3)).direction must be equalTo Direction(1, 1)
    }
    "be up and left" in {
      BadCell((2, 2)).direction must be equalTo Direction(1, -1)
    }
    "be down and left" in {
      BadCell((4, -4)).direction must be equalTo Direction(1, 1)
    }
  }
}