package bot

trait Grid {
  type Position = (Int, Int)
}

trait Direction {
  val x: Int
  val y: Int

  def +(direction: Direction): Direction = new Direction() {
    val x: Int = x + direction.x
    val y: Int = y + direction.x
  }
}

case object Up extends Direction {
  val x = 0
  val y = -1
}

case object Left extends Direction {
  val x = -1
  val y = 0
}