package bot.cell

import env.TestEnvironment
import org.junit.Test

class DirectionTest extends TestEnvironment with Directional {
  
  @Test def theForwardRightDirectionShouldBeReturned() {
    Direction(1,0).forwardR should equal(Direction(1, 1))
    Direction(1,-1).forwardR should equal(Direction(1, 0))
    Direction(0,-1).forwardR should equal(Direction(1, -1))
  }
  
  @Test def theForwardLeftDirectionShouldBeReturned() {
    Direction(1,0).forwardL should equal(Direction(1, -1))
    Direction(1,-1).forwardL should equal(Direction(0, -1))
    Direction(0,-1).forwardL should equal(Direction(-1, -1))
  }
  
  @Test def theBackRightDirectionShouldBeReturned() {
    Direction(1,0).backR should equal(Direction(-1, 1))
    Direction(1,-1).backR should equal(Direction(0, 1))
    Direction(0,-1).backR should equal(Direction(1, 1))
  }
  
  @Test def theBackLeftDirectionShouldBeReturned() {
    Direction(1,0).backL should equal(Direction(-1, -1))
    Direction(1,-1).backL should equal(Direction(-1, 0))
    Direction(0,-1).backL should equal(Direction(-1, 1))
  }
  
  @Test def thePerpRightDirectionShouldBeReturned() {
    Direction(1,0).perpR should equal(Direction(0, 1))
    Direction(1,-1).perpR should equal(Direction(1, 1))
    Direction(0,-1).perpR should equal(Direction(1, 0))
  }
  
  @Test def thePerpLeftDirectionShouldBeReturned() {
    Direction(1,0).perpL should equal(Direction(0, -1))
    Direction(1,-1).perpL should equal(Direction(-1, -1))
    Direction(0,-1).perpL should equal(Direction(-1, 0))
  }
  
  @Test def theReverseDirectionShouldBeReturned() {
    Direction(1,0).reverse should equal(Direction(-1, 0))
    Direction(1,-1).reverse should equal(Direction(-1, 1))
    Direction(0,-1).reverse should equal(Direction(0, 1))
  }
}