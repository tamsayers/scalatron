class ControlFunctionFactory {
  def create = new ControlFunction().respond _
}

case class Welcome(name: String, path: String, apocalypse: Int, round: Int) {
  override def toString() = "Name=" + name + "; path=" + path + "; apocalypse=" + apocalypse + "; round=" + round
}

class ControlFunction {
  val WelcomeRegex = "Welcome\\(name=(.+),path=(.+),apocalypse=([0-9]+),round=([0-9]+)\\)".r

  def respond(input: String): String = input match {
    case WelcomeRegex(name, path, apocalypse, round) => {
      println(Welcome(name, path, apocalypse.toInt, round.toInt))
      "Move(Say(text=Hello from " + name + "))"
    }
  }
}