package reductions

import scala.annotation._
import org.scalameter._
import common._

object ParallelParenthesesBalancingRunner {

  @volatile var seqResult = false

  @volatile var parResult = false

  val standardConfig = config(
    Key.exec.minWarmupRuns -> 40,
    Key.exec.maxWarmupRuns -> 80,
    Key.exec.benchRuns -> 120,
    Key.verbose -> true
  ) withWarmer(new Warmer.Default)

  def main(args: Array[String]): Unit = {
    val length = 100000000
    val chars = new Array[Char](length)
    val threshold = 10000
    val seqtime = standardConfig measure {
      seqResult = ParallelParenthesesBalancing.balance(chars)
    }
    println(s"sequential result = $seqResult")
    println(s"sequential balancing time: $seqtime ms")

    val fjtime = standardConfig measure {
      parResult = ParallelParenthesesBalancing.parBalance(chars, threshold)
    }
    println(s"parallel result = $parResult")
    println(s"parallel balancing time: $fjtime ms")
    println(s"speedup: ${seqtime / fjtime}")
  }
}

object ParallelParenthesesBalancing {

  /** Returns `true` iff the parentheses in the input `chars` are balanced.
   */
  def balance(chars: Array[Char]): Boolean = {
    def currentlyOpen(nOpen: Int, chars: Array[Char]): Boolean = {
      if (chars.isEmpty) {
        nOpen == 0
      } else if (nOpen < 0) {
        false
      } else {
        val head = if (chars.head == '(') 1 else if (chars.head == ')') -1 else 0
        currentlyOpen(nOpen + head, chars.tail)
      }
    }
    currentlyOpen(0, chars)
  }

  /** Returns `true` iff the parentheses in the input `chars` are balanced.
   */
  def parBalance(chars: Array[Char], threshold: Int): Boolean = {

    def traverse(idx: Int, until: Int, arg1: Int, arg2: Int) : (Int, Int) = {
      if(idx >= until) (arg1, arg2)
      else {
        traverse(
          idx + 1,
          until,
          arg1 + (if(chars(idx) == '(') 1 else 0),
          arg2 + (if(chars(idx) == ')') 1 else 0)
        )
      }
    }

    def reduce(from: Int, until: Int) : (Int, Int) = {
      if(until - from < threshold) traverse(from, until, 0, 0)
      else {
        val mid = (until - from) / 2
        val (o, c) = parallel(
          reduce(from, mid),
          reduce(mid, until))
        (0, 0)
      }
    }

    //reduce(0, chars.length) == ???
    balance(chars)
  }

  // For those who want more:
  // Prove that your reduction operator is associative!

}
