val r1 = new Rational(1, 2)
r1.numer
val r2 = new Rational(8, 4)
r1 + r2
val r3 = new Rational(3, 2)
r1 - r2 - r3
val r4 = new Rational(2)
//val r5 = new Rational(3, 0)
r1 max r3


class Rational(x: Int, y: Int){
  require(y != 0, "denominatore cannot be zero")
  private def gcd(a: Int, b: Int): Int = if(b == 0) a else gcd(b, a % b)

  def g = gcd(x, y)
  def numer = x / g
  def denom = y / g

  def this(x: Int) = this(x, 1)

  def < (that: Rational): Boolean =  this.numer * that.denom < that.numer * that.denom

  def max (that: Rational) = if (this < that) that else this

  def unary_- = new Rational(-numer, denom)

  def + (that: Rational): Rational =
    new Rational(
      this.numer * that.denom + that.numer * that.denom,
      this.denom * that.denom
    )

  def - (that: Rational): Rational =
    this + -that

  override def toString = {
    if(denom == 1) numer.toString() else numer + "/" + denom
  }
}