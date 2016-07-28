// Thanks to lazy stream we can work on infinite loop

// Infinite loop
def from(n: Int): Stream[Int] = n #:: from(n + 1)

//Stream of all natural numbers
val nats = from(0)

// Stream of all multiple of four
val m4s = nats map (_ * 4)

(m4s take 5).toList

//Sieve of eratosthenes
def sieve(s: Stream[Int]): Stream[Int] =
  s.head #:: sieve(s.tail filter (_ % s.head != 0))

val primes = sieve(from(2))

(primes take 100).toList

// We can remove isGoodEnough : we can express the concept of converging
// without having to worry about when to terminate it
def sqrtStream(x: Double): Stream[Double] = {
  def improve(guess: Double) = (guess + x / guess) / 2
  lazy val guesses: Stream[Double] = 1 #:: (guesses map improve)
  guesses
}

sqrtStream(2).take(10).toList

// We can add determiniation criterion later
def isGoodEnough(guess: Double, x: Double) = {
  math.abs((guess * guess - x) / x) < 0.001
}

sqrtStream(2) filter (isGoodEnough(_, 2)) head

