val xs = List(12,2,41,4,6,8,10,16,20,18)

xs.map(x => 2 * x)

xs.filter(x => x > 10)

xs.filterNot(x => x > 10)

xs.partition(x => x > 10)

xs.takeWhile(x => x > 10)

xs.dropWhile(x => x > 10)

xs.span(x => x > 10)

def pack[T](xs: List[T]): List[List[T]] = xs match {
  case Nil => Nil
  case x :: xs1 =>
    val (first, rest) = xs span (y => y == x)
    first :: pack(rest)
}

def encode[T](xs: List[T]): List[(T, Int)] =
  pack(xs) map (ys => (ys.head, ys.length))

pack(List("a", "a", "a", "b", "c", "c", "a"))

encode(List("a", "a", "a", "b", "c", "c", "a"))

val nums = List(1,2,5,6)

nums reduceLeft (_ + _)

nums reduceLeft (_ * _)

(nums foldRight List(8,9)) (_ :: _)