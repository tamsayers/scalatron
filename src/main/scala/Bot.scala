class ControlFunctionFactory {
    def create = new ControlFunction().respond _
}

class ControlFunction {
    def respond(input: String): String = "Status(text=Hello World)"
}