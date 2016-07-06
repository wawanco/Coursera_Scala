val fruit = List("apples", "oranges")
val nums  = List(5, 12, 8, 3, 44)
val empty = List()

"pears" :: fruit
fruit.::("pears")

val cons = 2 :: 1 :: Nil

fruit.isEmpty

nums.head

("pears" :: fruit).tail

//java.util.NoSuchElementException
// empty.head

def insert(x: Int, xs: List[Int]): List[Int] = xs match {
  case List() => x :: Nil
  case y :: ys =>
    if(x <= y) x :: xs
    else y :: insert(x, ys)
}

def isort(xs: List[Int]): List[Int] = xs match {
  case Nil => List()
  case y :: ys => insert(y, isort(ys))
}

isort(nums)
