package bot

trait GameView extends Grid {
  trait Direction

  trait Cell
  case object Empty extends Cell
  case object Occluded extends Cell
  case object Wall extends Cell
  case object Bot extends Cell
  case object EnemyBot extends Cell
  case object MiniBot extends Cell
  case object EnemyMiniBot extends Cell
  case object Zugar extends Cell
  case object Toxifera extends Cell
  case object Fluppet extends Cell
  case object Snorg extends Cell

  class View(cells: Map[Position, Cell]) {
    override def toString = cells.mkString("; ")
  }

  object ViewBuilder {
    def cellFor(c: Char): Cell = {
      println("cell:" + c + ":")
      c match {
        case '?' => Occluded
        case '_' => Empty
        case 'W' => Wall
        case 'M' => Bot
        case 'm' => EnemyBot
        case 'S' => MiniBot
        case 's' => EnemyMiniBot
        case 'P' => Zugar
        case 'p' => Toxifera
        case 'B' => Fluppet
        case 'b' => Snorg
      }
    }

    def isInteresting(cell: Cell): Boolean = cell match {
      case Empty | Occluded => false
      case _ => true
    }

    def parse(view: String): View = {
      val gridSize = Math.sqrt(view.length).toInt
      val gridOffset = (gridSize - 1) / 2

      val grid = for {
        (cells, row) <- view.grouped(gridSize).zipWithIndex
        (cell, column) <- cells.zipWithIndex map (x => (cellFor(x._1), x._2))
        if isInteresting(cell)
      } yield ((row - gridOffset, column - gridOffset), cell)

      new View(grid.toMap)
    }
  }
}