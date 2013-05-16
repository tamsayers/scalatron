package bot

trait Cell
trait GoodCell extends Cell
trait BadCell extends Cell

case object Unimportant extends Cell
case object Wall extends Cell
case object Bot extends GoodCell
case object EnemyBot extends BadCell
case object MiniBot extends GoodCell
case object EnemyMiniBot extends BadCell
case object Zugar extends GoodCell
case object Toxifera extends BadCell
case object Fluppet extends GoodCell
case object Snorg extends BadCell

trait ViewBuilderComponent extends Grid {
  val viewBuilder = ViewBuilder

  object ViewBuilder {
    def cellFor(c: Char): Cell = {
      c match {
        case '?' | '_' => Unimportant
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

    def parse(view: String): View = {
      def isInteresting(cell: Cell): Boolean = cell match {
        case Unimportant => false
        case _ => true
      }

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

  class View(populatedCells: Map[Position, Cell]) {
    val cells = populatedCells.withDefault(x => Unimportant)

    def isSafe(direction: Direction): Boolean = {
      cells.get(direction.toPosition) match {
        case Some(Wall) => false
        case _ => true
      }
    }

    override def toString = cells.mkString("; ")
  }

}
