package bot

trait Grid {
  type Position = (Int, Int)
}

trait Direction {
  val x: Int
  val y: Int

  def +(that: Direction): Direction = new Direction() {
    val x: Int = this.x + that.x
    val y: Int = this.y + that.x
  }

  def reverse: Direction = new Direction() {
    val x: Int = -x
    val y: Int = -y
  }
}

object Up extends Direction {
  val x = 0
  val y = -1
}

object Down extends Direction {
  val x = 0
  val y = 1
}

object Left extends Direction {
  val x = -1
  val y = 0
}

object Right extends Direction {
  val x = 1
  val y = 0
}