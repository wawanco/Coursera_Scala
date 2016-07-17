val fruit = Set("apple", "banana", "pear")

val s = (1 to 6).toSet

s map (_ + 2)
fruit filter (_.startsWith("app"))
s.nonEmpty

List(1, 1, 2, 3, 3).toSet

s contains 5

(List(0, 3, 1) zip (1 to 3)).flatMap {case (i, j) => List(i + j, i - j)} toSet

/*
(for {
  (i, j) <- List(0, 3, 1) zip (1 to 3)
} yield (i + j, i - j)).flatten
*/

// n-queens
def isSafe_Mine(col: Int, queens: List[Int]): Boolean = {
  val row = queens.length
  val dangerousCols =
    queens ::: (queens zip (1 to row)).flatMap {case (i, j) => List(i + j, i - j)}
  !(dangerousCols contains col)
}

def isSafe(col: Int, queens: List[Int]): Boolean = {
  val row = queens.length
  val queensRowCol = (row - 1 to 0 by -1) zip queens
  queensRowCol forall {
    case (r, c) => col != c && math.abs(col - c) != row -r
  }
}


def queens(n: Int): Set[List[Int]] = {
  def placeQueens(k: Int): Set[List[Int]] =
    if(k == 0) Set(List())
    else
      for {
        queens <- placeQueens(k - 1)
        col <- 0 until n
        if isSafe(col, queens)
      } yield col :: queens
  placeQueens(n)
}

def show(queens: List[Int]): String = {
  val lines =
    for (col <- queens.reverse)
      yield Vector.fill(queens.length)("* ").updated(col, "X ").mkString
  "\n" + (lines mkString "\n")
}

(queens(8) take 3 map show).mkString("\n")