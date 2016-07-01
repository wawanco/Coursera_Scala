var tolerance = 0.0001

def abs(x: Double): Double = if (x < 0) -x else x

def isCloseEnough(x: Double, y: Double): Boolean = abs(x - y) / x < tolerance

def fixedPoint(f: Double => Double)(init: Double): Double ={
  def iterate(f: Double => Double)(guess: Double): Double =
    if (isCloseEnough(guess, f(guess))) guess else iterate(f)(f(guess))
  iterate(f)(init)
}

fixedPoint(x => 1 + x / 2)(1.0)

def sqrt(x: Double): Double = fixedPoint(y => (y + x / y) / 2)(1.0)

sqrt(2)