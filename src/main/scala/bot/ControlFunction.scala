package bot

class ControlFunctionFactory {
  this: ControlFunctionComponent =>

  def create = controlFunction.respond _
}

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

trait EnvComponent {
  var game: Welcome = _
}

trait ControlFunctionComponent {
  this: ResponderComponent =>

  def controlFunction = new ControlFunction(responder)

  class ControlFunction(responder: Responder) {
    val WelcomeRegex = "Welcome\\(name=(.+),path=(.+),apocalypse=([0-9]+),round=([0-9]+)\\)".r
    val ReactMasterRegex = "React\\(generation=([0-9]+),name=(.+),time=([0-9]+),view=(.+),energy=([0-9]+)\\)".r

    def respond(input: String): String = input match {
      case WelcomeRegex(name, path, apocalypse, round) =>
        responder.welcome(Welcome(name, path, apocalypse.toInt, round.toInt))
      case ReactMasterRegex(generation, name, time, view, energy) =>
        responder.master(Master(generation.toInt, name, time.toInt, view, energy.toInt))
    }
  }
}