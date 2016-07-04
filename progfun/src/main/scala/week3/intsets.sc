val t1 = new NonEmpty(3, Empty, Empty)
val t2 = t1 incl 4

abstract class IntSet {
  def incl(x: Int): IntSet
  def contains(x: Int): Boolean
  def union(other: IntSet): IntSet
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

  override def union(other: IntSet) =
    ((left union right) union other) incl elem

  override def toString = "{" + left + elem + right + "}"
}

// Sigleton
object Empty extends IntSet {
  override def incl(x: Int): IntSet =
    new NonEmpty(x, Empty, Empty)

  override def contains(x: Int): Boolean = false

  override def union(other: Int) = other

  override def toString = "."
}