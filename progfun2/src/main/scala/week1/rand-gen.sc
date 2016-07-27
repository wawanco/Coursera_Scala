trait Generator[+T] {
  self => // alias for this

  def generate: T

  def map[S](f: T => S): Generator[S] = new Generator[S] {
    override def generate: S = f(self.generate)
  }

  def flatMap[S](f: T => Generator[S]): Generator[S] = new Generator[S] {
    override def generate: S = f(self.generate).generate
  }
}

def single[T](x: T): Generator[T] = new Generator[T] {
  override def generate: T = x
}

// Implementation of Int with java library
val integers = new Generator[Int] {
  val rand = new java.util.Random
  def generate: Int = rand.nextInt
}

def choose(lo: Int, hi: Int): Generator[Int] =
  for(x <- integers) yield (if(x > 0) x else -x) % (hi - lo) + lo

def oneOf[T](xs: T*): Generator[T] =
  for(idx <- choose(0, xs.length)) yield xs(idx)

// Basic implementation
val pairs1 = new Generator[(Int, Int)] {
  def generate: (Int, Int) = (integers.generate, integers.generate)
}

// Implementation with map and flatMap
val booleans = integers.map(_ > 0)

def pairs[T, U](t: Generator[T], u: Generator[U]): Generator[(T, U)] = t flatMap {
  x => u map {y => (x, y)}
}

// Equivalent implementation with for expr.
val booleansFor = for(x <- integers) yield x > 0

def pairsFor[T, U](t: Generator[T], u: Generator[U]): Generator[(T, U)] = for {
  x <- t
  y <- u
} yield(x, y)

// List Generator
def lists: Generator[List[Int]] = for {
  isEmpty <- booleans
  list <- if(isEmpty) emptyList else nonEmptyList
} yield list

def emptyList = single(Nil)

def nonEmptyList = for {
  head <- integers
  tail <- lists
} yield head :: tail

// Tree Generator
trait Tree

case class Inner(left: Tree, right: Tree) extends Tree

case class Leaf(x: Int) extends Tree

def trees: Generator[Tree] = for {
  isLeaf <- booleans
  trees <- if(isLeaf) leaves else inners
} yield trees

def leaves: Generator[Leaf] = for {
  x <- integers
} yield Leaf(x)


def inners: Generator[Inner] = for {
  r <- trees
  l <- trees
} yield Inner(r, l)

// Calls
integers.generate
booleans.generate
booleansFor.generate
pairs(integers, booleans).generate
pairsFor(integers, booleans).generate

for(_ <- 1 to 10) yield choose(5, 10).generate

lists.generate