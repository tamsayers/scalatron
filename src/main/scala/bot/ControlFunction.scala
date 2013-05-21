package bot

case class Welcome(name: String, path: String, apocalypse: Int, round: Int) {
  override def toString() = "name=" + name +
    "; path=" + path +
    "; apocalypse=" + apocalypse +
    "; round=" + round
}

case class Master(generation: Int, name: String, time: Int, view: String, energy: Int) {
  override def toString() = "generation=" + name +
    "; name=" + name +
    "; time=" + time +
    "; view=" + view +
    "; energy=" + energy
}

trait EnvComponent extends Grid {
  var game: Welcome = _
  var currentDirection: Position = Up
}

trait ControlFunctionComponent {
  self: ResponderComponent =>

  val controlFunction = new ControlFunction()

  class ControlFunction() {
    val WelcomeRegex = "Welcome\\(name=(.+),path=(.+),apocalypse=([0-9]+),round=([0-9]+)\\)".r
    val ReactMasterRegex = "React\\(generation=([0-9]+),time=([0-9]+),view=(.+),energy=([0-9]+),name=(.+)\\)".r

    def respond(input: String): String = input match {
      case WelcomeRegex(name, path, apocalypse, round) =>
        responder.welcome(Welcome(name, path, apocalypse.toInt, round.toInt))
      case ReactMasterRegex(generation, time, view, energy, name) =>
        responder.master(Master(generation.toInt, name, time.toInt, view, energy.toInt))
    }
  }
}