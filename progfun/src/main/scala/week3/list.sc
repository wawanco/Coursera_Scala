trait List[T] {
  def isEmpty: Boolean
  def head: T
  def tail: List[T]
  override def toString = "[" + head + ", " + tail
}

class Nil[T] extends List[T]{
  val isEmpty = true
  def head = throw new NoSuchElementException("Nil.head")
  def tail = throw new NoSuchElementException("Nil.tail")
  override def toString = "]"
}

class Cons[T](val head: T, val tail: List[T]) extends List[T]{
  val isEmpty = false
}

// Function with type parameters
def singleton[T](elem: T) = new Cons[T](elem, new Nil[T])
singleton(1)
singleton(true)


def nth[T](n: Int, xs: List[T]): T = {
  if (xs.isEmpty || n < 0) throw new IndexOutOfBoundsException
  else if (n == 0) xs.head
  else nth(n - 1, xs.tail)
}

val lst = new Cons(1, new Cons(3, new Cons(5, new Nil)))

nth(1, lst)
nth(4, lst)
nth(-1, lst)