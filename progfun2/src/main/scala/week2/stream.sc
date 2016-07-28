// Stream avoid computing the tail of a sequence until it is needed
// for the evaluation result (which might be never).

Stream(1, 2, 3, 4)

(1 to 1000).toStream

def streamRange(lo: Int, hi: Int): Stream[Int] = {
  print(lo + " ")
  if (lo >= hi) Stream.empty
  else lo #:: streamRange(lo + 1, hi)
}

def listRange(lo: Int, hi: Int): List[Int] =
  if (lo >= hi) Nil
  else lo :: listRange(lo + 1, hi)

streamRange(1, 1000)
listRange(1, 1000)

streamRange(1, 1000) take 3 toList