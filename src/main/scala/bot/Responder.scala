package bot

trait ResponderComponent {
  this: EnvComponent =>
  def responder = new Responder()

  class Responder {
    def welcome(welcome: Welcome): String = {
      game = welcome
      Say("Hello from " + welcome.name).command
    }

    def master(master: Master): String = ""
  }
}

trait Command {
  def command: String
}

case class Say(message: String) extends Command {
  require(!List('|', ',', '=', '(').exists(c => message.contains(c)))

  def command: String = "Say(text=" + message + ")"
}

case class Move(direction: Direction) extends Command {
  def command: String = "Move(direction=" + direction.x + ":" + direction.y + ")"
}