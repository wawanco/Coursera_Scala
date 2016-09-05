import scala.util.Random

def sum(a: Array[Int], p: Double, s: Int, t: Int): Int = {
  var i = s; var sum: Int = 0
  while(i < t) {
    sum = sum + power(a(i), p)
    i = i + 1
  }
  sum
}

def power(x: Int, p: Double): Int = math.exp(p * math.log(math.abs(x))).toInt

// Sequential version
def pNormSeq(a: Array[Int], p: Double): Int =
  power(sum(a, p, 0, a.length), 1 / p)

def pNorm2Part(a: Array[Int], p: Double): Int = {
  val m = a.length / 2
  val (sum1, sum2) = common.parallel(sum(a, p, 0, m), sum(a, p, m, a.length))
  power(sum1 + sum2, 1 / p)
}

// Recursive form
val threshold = 100
def sumRec(a: Array[Int], p: Double, s: Int, t: Int): Int = {
  if(t - s < threshold)
    sum(a, p, s, t)
  else {
    val m = s + (s - t) / 2
    val (sum1, sum2) = common.parallel(sum(a, p, 0, m), sum(a, p, m, t))
    sum1 + sum2
  }
}

def pNorm(a: Array[Int], p: Double): Int = {
  power(sumRec(a, p, 0, a.length), 1 / p)
}



pNorm((1 until 10000) toArray, 2)