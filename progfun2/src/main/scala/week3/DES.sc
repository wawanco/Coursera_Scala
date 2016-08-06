// Discrete Event Simulation

class Wire extends Simulation{
  def getSignal() = ???
  def addAction(action: Action) = ???
}

class Circuits

trait Simulation {
  type Action = () => Unit
  case class Event(time: Int, action: Action)
  private type Agenda = List[Event]
  private var agenda: Agenda = List()
  private var curtime = 0
  def currentTime: Int = curtime

  def afterDelay(delay: Int)(block: => Unit): Unit = {
    val item = Event(currentTime + delay, () => block)
    agenda = insert(agenda, item)
  }

  def insert(ag: Agenda, item: Event): Agenda = ag match {
    case first :: rest if first.time < item.time =>
      first :: insert(rest, item)
    case _ => item :: ag
  }

  private def loop(): Unit = agenda match {
    case first :: rest =>
      agenda = rest
      curtime = first.time
      first.action()
      loop()
    case Nil =>
  }

  def run(): Unit = {
    afterDelay(0){
      println("*** simulation started, time = " + currentTime + " ***")
    }
    loop()
  }

  def probe(name: String, wire: Wire): Unit = {
    def probeAction(): Unit = {
      println(s"$name $currentTime value = ${wire.getSignal}")
    }
    wire addAction probeAction
  }
}

trait Parameters {
  def InverterDelay = 2
  def AndGateDelay = 3
  def OrGateDelay = 5
}

