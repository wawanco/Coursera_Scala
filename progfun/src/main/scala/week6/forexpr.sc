// All couples (i, j) such that 1 <= j < i < n where i + j is prime
def isPrime(n: Int): Boolean =
(2 until n) forall (d => n % d != 0)

val n = 7

// Way to flatten the nested loop
// xs flatMap f = (xs map f).flatten
((1 until n) map (i => (1 until i) map (j => (i, j)))).flatten ==
  ((1 until n) flatMap (i => (1 until i) map (j => (i, j))))


// Works but makes most people's head hurt!
((1 until n)
  flatMap (i => (1 until i)
    map (j => (i, j)))) filter {case (i, j) => isPrime(i + j)}

case class Person(name: String, age: Int)

val persons = List(Person("Joe", 19), Person("Ana", 21), Person("Rachel", 25))

// Names of person over 20 years old
for(p <- persons if p.age > 20) yield p.name

// Equivalent to
persons filter (p => p.age > 20) map (p => p.name)

// Back to test if sum is prime
for {
  i <- 1 until n
  j <- 1 until i
  if isPrime(i + j)
} yield (i, j)

// Back to scalar product
def scalarProd(xs: Vector[Double], ys: Vector[Double]): Double =
  (for ((x, y) <- xs zip ys) yield x * y).sum

scalarProd(Vector(0, 1, 0), Vector(1, 0, 0))
scalarProd(Vector(1, 2), Vector(2, 1))
