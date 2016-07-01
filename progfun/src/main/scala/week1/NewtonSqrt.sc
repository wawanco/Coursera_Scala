def sqrt(x: Double): Double = {
  def abs(x: Double): Double = if (x >= 0) x else -x

  def isGoodEnough(guess: Double): Boolean = abs((guess * guess) - x) < 0.001

  def mean(x1: Double, x2: Double): Double = .5 * (x1 + x2)

  def improve(guess: Double): Double = mean(guess, x / guess)

  def sqrtIter(guess: Double): Double =
    if(isGoodEnough(guess)) guess else sqrtIter(improve(guess))

  sqrtIter(1)
}

sqrt(236)