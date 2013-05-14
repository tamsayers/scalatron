package bot

import org.junit.Assert._
import org.hamcrest.Matchers._
import org.junit.Test
import bot.cell.Bad
import cell._
import org.junit.Ignore
import env.TestEnvironment

@Ignore
class ViewTest extends TestEnvironment {

  case class BadCell(position: (Int, Int), energy: Int, range: Int = 0) extends Bad

  @Test
  def the_cells_not_to_move_into_in_a_given_direction_should_be_returned_in() {
    val cells = List(BadCell((1, 1), -100), BadCell((-2, 1), -200), BadCell((3, 1), -300, 1))

    assertThat(botSight.toAvoid(cells), is(List(Direction(1, -1))))
  }
  
  @Test
  def the_cells_not_to_move_into_in_a_given_direction_should_be_returned_for_range_directions() {
    val cells = List(BadCell((1, 1), -100), BadCell((-2, 1), -200, 1), BadCell((3, 1), -300, 1))

    assertThat(botSight.toAvoid(cells), is(List(Direction(1, -1), Direction(-1, -1), Direction(-1, 0))))
  }
}
