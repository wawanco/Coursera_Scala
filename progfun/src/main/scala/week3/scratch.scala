package week3
import week2.Rational

/**
  * Created by trodriguez on 01/07/2016.
  */
object scratch {
  def main(args: Array[String]): Unit = {
    println(new Rational(5, 10))
    error("Nothing is returned.")
  }

  // Nothing is a type saying that nothing is returned (abnormal termination)
  def error(msg: String): Nothing = throw new Error(msg)
}
