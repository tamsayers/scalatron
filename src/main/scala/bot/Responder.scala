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
  this: ViewBuilderComponent =>
  val masterResponse = new MasterResponse()

  class MasterResponse {
    def getFor(master: Master): Command = {
      Move(Up)
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

case class Move(direction: Direction) extends Command {
  def command: String = {
    val (x, y) = direction.toPosition
    "Move(direction=" + x + ":" + y + ")"
  }
}