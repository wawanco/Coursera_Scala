
abstract class IntSet {
  def incl(x: Int): IntSet
  def contains(x: Int): Boolean
}

class NonEmpty(elem: Int, left: IntSet, right: IntSet) extends IntSet {
  override def contains(x: Int): Boolean =
    if (x > elem) right contains x
    else if(x < elem) left contains x
    else true

  override def incl(x: Int): IntSet =
    if (x > elem) new NonEmpty(elem, left, right incl x)
    else if (x < elem) new NonEmpty(elem, left incl x, right)
    else this
}

class Empty extends IntSet {
  override def incl(x: Int): IntSet =
    new NonEmpty(x, new Empty(), new Empty())
  override def contains(x: Int): Boolean = false
}