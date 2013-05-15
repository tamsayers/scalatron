package bot

trait GameView {
  type Position = (Int, Int)

  trait Cell
  case object Empty extends Cell
  case object Occluded extends Cell
  case object Wall extends Cell
  case object Bot extends Cell
  case object EnemyBot extends Cell
  case object MiniBot extends Cell
  case object EnemyMiniBot extends Cell
  case object Toxifera extends Cell
  case object Fluppet extends Cell
  case object Snorg extends Cell

  class View(cells: Map[Position, Cell]) {
    override def toString = cells.mkString("; ")
  }

  object ViewBuilder {
    def parse(view: String): View = {
      val gridSize = Math.sqrt(view.length).toInt
      val cells = Map[Position, Cell]()
      new View(cells)
    }
  }
}