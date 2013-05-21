package bot

trait ResponderComponent {
  this: EnvComponent with MasterResponseComponent =>
  val responder = new Responder()

  class Responder {
    def welcome(welcome: Welcome): String = {
      game = welcome
      Say(welcome.name).command
    }

    def master(master: Master): String = masterResponse.getFor(master).command
  }
}

trait MasterResponseComponent {
  this: ViewBuilderComponent with EnvComponent =>
  val masterResponse = new MasterResponse()

  class MasterResponse() {
    def getFor(master: Master): Command = {
      val view = viewBuilder.parse(master.view)

      if (!view.isSafe(currentDirection)) currentDirection = currentDirection.reverse

      Move(currentDirection)
    }
  }
}

trait Command {
  def command: String
}

case class Say(message: String) extends Command {
  require(!List('|', ',', '=', '(').exists(c => message.contains(c)))
  require(message.length <= 10)

  def command: String = "Say(text=" + message + ")"
}

case class Move(direction: Position) extends Command {
  def command: String = {
    "Move(direction=" + direction.x + ":" + direction.y + ")"
  }
}