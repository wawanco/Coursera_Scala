class HelloThread extends Thread {
  // lack of atomicity
  override def run() {
    println("Hello ")
    println("World!")
  }
}

val t = new HelloThread
val s = new HelloThread

t.start() // a new thread of type hello thread starts
s.start() // a new thread of type hello thread starts
t.join() // blocks execution until t has finished
s.join() // a new thread of type hello thread starts
