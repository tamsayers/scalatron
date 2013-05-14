package bot

import org.junit.Test
import org.scalatest.mock.MockitoSugar
import org.mockito.Mockito.when
import org.hamcrest.MatcherAssert._
import org.hamcrest.Matchers._
import bot.cell._
import org.junit.Ignore
import org.junit.Before
import org.scalatest.matchers.ShouldMatchers
import env.TestEnvironment

class FoodFinderTest extends TestEnvironment {
	override val foodFinder = new FoodFinder

	val cells = List()
	val foodCell = mock[Zugar]
	val foodDirection = Direction(0,1)

	@Before def setUp() {
	  when(botSight.bestFood(cells)).thenReturn(Option(foodCell))
	  when(botSight.bestFood(Nil)).thenReturn(None)
	  when(foodCell.direction).thenReturn(foodDirection)	  
	}

	@Test def theDirectionToTheBestFoodShouldBeReturnedIfThereIsNothingToAvoid() {	  
	  foodFinder.findFood(null, cells, Direction => true) should equal (foodDirection)
	}
	
	@Test def theNextBestDirectionShouldBeReturnedIfTheFoodDirectionShouldBeAvoided() {  
	  def isOk(dir:Direction) = dir != foodDirection
	  
	  foodFinder.findFood(null, cells, isOk) should equal (foodDirection.forwardR)
	}
	
	@Test def ifTheFoodAndNextBestDirectionAreToBeAvoidedThenTheThirdBestShouldBeReturned() {
	  def isOk(direction:Direction) = direction match {
	    case dir if dir == foodDirection => false
	    case dir if dir == foodDirection.forwardR => false
	    case _ => true
	  }
	  
	  foodFinder.findFood(null, cells, isOk) should equal (foodDirection.forwardL)
	}
	
	@Test def ifTheForwardDirectionsAreNotOkTheFirstPerpendicularDirectionShouldBeReturned() {
	  def isOk(direction:Direction) = direction match {
	    case dir if dir == foodDirection => false
	    case dir if dir == foodDirection.forwardR => false
	    case dir if dir == foodDirection.forwardL => false
	    case _ => true
	  }
	  
	  foodFinder.findFood(null, cells, isOk) should equal (foodDirection.perpR)
	}
	
	@Test def ifTheForwardDirectionsAndPerp1AreNotOkTheSecondPerpendicularDirectionShouldBeReturned() {
	  def isOk(direction:Direction) = direction match {
	    case dir if dir == foodDirection => false
	    case dir if dir == foodDirection.forwardR => false
	    case dir if dir == foodDirection.forwardL => false
	    case dir if dir == foodDirection.perpR => false
	    case _ => true
	  }
	  
	  foodFinder.findFood(null, cells, isOk) should equal (foodDirection.perpL)
	}
	
	@Test def ifNonOfTheFoodDirectionsAreOkTheOriginalDirectionShouldBeReturnedIfOk() {
	  def isOk(direction:Direction) = direction match {
	    case dir if dir == foodDirection => false
	    case dir if dir == foodDirection.forwardR => false
	    case dir if dir == foodDirection.forwardL => false
	    case dir if dir == foodDirection.perpR => false
	    case dir if dir == foodDirection.perpL => false
	    case _ => true
	  }
	  
	  val botDirection = foodDirection.reverse
	  
	  foodFinder.findFood(botDirection, cells, isOk) should equal (botDirection)
	}
}