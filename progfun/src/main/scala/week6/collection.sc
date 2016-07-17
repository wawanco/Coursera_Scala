
// Vector: access log(n)
// List: access n
val nums = Vector(1,2,3)

// Append vector
4 +: nums
nums :+ 4

// Java String and Array support some Sequence specific functions
val s = "Hello World!"
s filter (c => c.isUpper)

val xs = Array(2,4,6)
xs map (x => x * 2)

// Range : lower bound, upper bound, step
1 to 5
1 until 5
1 to 10 by 3
6 to 1 by -2

// Other useful methods
s exists (c => c.isUpper)

xs forall (x => x % 2 == 0)

nums zip s

List((1, "a"), (2, "b")) unzip

nums flatMap (x => List(x - 1, x, x + 1))

nums.sum

nums.max
nums.min

// Nested loop
val m = 3
val n = 2
(1 to m) flatMap(x => (1 to n) map (y => (x, y)))

// Scalar product
def scalarProd(xs: Vector[Double], ys: Vector[Double]): Double =
  (xs zip ys).map({ case (x, y) => x * y }).sum

scalarProd(Vector(0, 1, 0), Vector(1, 0, 0))
scalarProd(Vector(1, 2), Vector(2, 1))

// Test prime number
def isPrime(n: Int): Boolean =
  (2 until n) forall (d => n % d != 0)

isPrime(4)
isPrime(11)
isPrime(2345)