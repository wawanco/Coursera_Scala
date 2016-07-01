package funsets

object Main extends App {
  import FunSets._
  println(contains(singletonSet(1), 1))
  val s = union(singletonSet(0), union(union(singletonSet(2), singletonSet(4)), singletonSet(6)))
  printSet(s)
  printSet(map(s, x => 2 * x))
}
