package calculator

object Polynomial {
  def computeDelta(a: Signal[Double], b: Signal[Double],
      c: Signal[Double]): Signal[Double] = Signal {
    val sA = a()
    val sB = b()
    val sC = c()
    sB * sB - 4 * sA * sC
  }

  def computeSolutions(a: Signal[Double], b: Signal[Double],
      c: Signal[Double], delta: Signal[Double]): Signal[Set[Double]] = Signal {
    val sDelta = delta()
    if(sDelta < 0) Set()
    else if(sDelta == 0) Set(-b() / (2 * a()))
    else Set((-b() + math.sqrt(sDelta)) / (2 * a()), (-b() - math.sqrt(sDelta)) / (2 * a()))
  }
}
