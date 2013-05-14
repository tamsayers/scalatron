package bot

import org.specs2.mock.Mockito
import org.specs2.mutable.SpecificationWithJUnit
import bot.cell.Bad
import bot.cell.EnemyMiniBot
import bot.cell.Fluppet
import bot.cell.Food
import bot.cell.MiniBot
import bot.cell.Snorg
import bot.cell.Toxifera
import cell._
import org.junit.Ignore
import org.junit.Test
import env.TestEnvironment

class ViewSpec extends TestEnvironment {
  val chars = "S    " +
    "   B " +
    " s   " +
    "   p " +
    "  b  "

  val coords = List(MiniBot(-2, 2), Fluppet(1, 1), EnemyMiniBot(-1, 0), Toxifera(1, -1), Snorg(0, -2))

  @Test def theOccupiedCellsShouldBeReturned() {
    botSight.occupiedCells(chars).toList should equal(coords)
  }

  case class FoodCell(position: (Int, Int), energy: Int) extends Food

  @Test def theBestFoodCellShouldBeReturned() {
    val cells = List(FoodCell((1, 1), 100), FoodCell((1, 1), 300), FoodCell((1, 1), 200))

    botSight.bestFood(cells).get should equal(cells(1))
  }

  @Test def theFoodCellSWithTheHighestEnergyShouldBeReturnedForEqualValues() {
    val cells = List(FoodCell((1, 1), 100), FoodCell((-2, 1), 200), FoodCell((3, 1), 300))

    botSight.bestFood(cells).get should equal(cells(2))

  }

  case class BadCell(position: (Int, Int), energy: Int, range: Int = 0) extends Bad

  @Test def theCellsNotToMoveIntoInAGivenDirectionShouldBeBeturned() {
    val cells = List(BadCell((1, 1), -100), BadCell((-2, 1), -200), BadCell((3, 1), -300, 1))

    botSight.toAvoid(cells) should equal(List(Direction(1, -1)))
  }
  
  @Test def theCellsNotToMoveIntoInAGivenDirectionShouldBeReturnedForRangeDirections() {
    val cells = List(BadCell((1, 1), -100), BadCell((-2, 1), -200, 1), BadCell((3, 1), -300, 1))

    botSight.toAvoid(cells) should equal(List(Direction(1, -1), Direction(-1, -1), Direction(-1, 0)))
  }

}
