package quickcheck

import common._

import org.scalacheck._
import Arbitrary._
import Gen._
import Prop._

abstract class QuickCheckHeap extends Properties("Heap") with IntHeap {

  lazy val genHeap: Gen[H] = for {
    elem <- arbitrary[A]
    heap <- oneOf(const(empty), genHeap)
  } yield insert(elem, heap)
  implicit lazy val arbHeap: Arbitrary[H] = Arbitrary(genHeap)

  property("gen1") = forAll { (h: H) =>
    val m = if (isEmpty(h)) 0 else findMin(h)
    findMin(insert(m, h)) == m
  }

  property("gen2") = forAll { (x: A, y: A) =>
    findMin(insert(y, insert(x, empty))) == (if(x >= y) y else x)
  }

  property("gen3") = forAll { (x: A) =>
    isEmpty(deleteMin(insert(x, empty)))
  }

  def unstack(heap: H): List[A] = {
    if (isEmpty(heap)) Nil
    else findMin(heap) :: unstack(deleteMin(heap))
  }

  property("gen4") = forAll { (h: H) =>
    val l = unstack(h)
    l.sorted == l
  }

  property("gen5") = forAll { (e1: A, e2: A, e3: A) =>
    val h = insert(e3, insert(e2, insert(e1, empty)))
    val l = unstack(h)
    l == List(e1, e2, e3).sorted
  }

  property("gen6") = forAll { (h1: H, h2: H) =>
    val m1 = findMin(h1)
    val m2 = findMin(h2)
    val m = findMin(meld(h1, h2))
    m == m1 || m == m2
  }


}
