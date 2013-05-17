package bot

trait Grid {
  type Position = (Int, Int)
}

trait Direction extends Grid {
  def +(that: Direction): Direction = {
    val (x, y) = toPosition
    val (thatX, thatY) = that.toPosition

    new Direction() { def toPosition = (x + thatX, y + thatY) }
  }

  def reverse: Direction = {
    val (x, y) = toPosition
    new Direction() { def toPosition = (-x, -y) }
  }

  def toPosition: Position
}

object Up extends Direction {
  def toPosition: Position = (0, -1)
}

object Down extends Direction {
  def toPosition: Position = Up.reverse.toPosition
}

object Left extends Direction {
  def toPosition: Position = (-1, 0)
}

object Right extends Direction {
  def toPosition: Position = Left.reverse.toPosition
}