package week3
<<<<<<< HEAD
// 
import week2._
=======
import week2.Rational

>>>>>>> 6f9152f418a826f5ad8ec1798f4c7dfef4c26cc0
/**
  * Created by trodriguez on 01/07/2016.
  */
object scratch {
<<<<<<< HEAD
  new Rational(3, 4)
=======
  def main(args: Array[String]): Unit = {
    println(new Rational(5, 10))
    error("Nothing is returned.")
  }

  // Nothing is a type saying that nothing is returned (abnormal termination)
  def error(msg: String): Nothing = throw new Error(msg)
>>>>>>> 6f9152f418a826f5ad8ec1798f4c7dfef4c26cc0
}
