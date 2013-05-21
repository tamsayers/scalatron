package bot

trait Grid {
  val Up = Position(0, -1)
  val Down = Up.reverse
  val Left = Position(-1, 0)
  val Right = Left.reverse
}

case class Position(val x: Int, val y: Int) {
  def +(that: Position): Position = Position(x + that.x, y + that.y)

  def reverse: Position = Position(-x, -y)
}