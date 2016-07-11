package patmat

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import patmat.Huffman._

@RunWith(classOf[JUnitRunner])
class HuffmanSuite extends FunSuite {
	trait TestTrees {
		val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
		val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
	}


  test("weight of a larger tree") {
    new TestTrees {
      assert(weight(t1) === 5)
      assert(weight(t2) === 9)
      assert(weight(Leaf('a', 2)) === 2)
    }
  }

  test("chars of a larger tree") {
    new TestTrees {
      assert(chars(t2) === List('a','b','d'))
      assert(chars(Leaf('a', 2)) === List('a'))
    }
  }

  test("make unique list ") {
    assert(makeUniqueChars(List('a', 'b', 'a'), Nil) === List('b', 'a'))
  }

  test("number of times a character occurs") {
    assert(times(List('a', 'b', 'a')) === List(('a', 2), ('b', 1)))
  }

  test("string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }

  test("lighter element") {
    assert(heavierElement(List(('a', 2), ('b', 1)), (0, 0)) === ('a', 2))
  }

  test("makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }

  test("singleton") {
    assert(!singleton(List(Leaf('e',1), Leaf('t',2), Leaf('x',3))))
    assert(singleton(List(Leaf('e',1))))
  }

  test("combine Nil") {
    assert(combine(Nil)==Nil)
  }

  test("combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }

  test("combine and insert middle leaf list") {
    val leaflist = List(Leaf('e', 3), Leaf('t', 3), Leaf('x', 4), Leaf('y', 10))
    assert(combine(leaflist) === List(Leaf('x', 4), Fork(Leaf('e',3),Leaf('t',3),List('e', 't'),6), Leaf('y', 10)))
  }

  test("combine and insert after leaf list") {
    val leaflist = List(Leaf('e', 3), Leaf('t', 3), Leaf('x', 4), Leaf('y', 5))
    assert(combine(leaflist) === List(Leaf('x', 4), Leaf('y', 5), Fork(Leaf('e',3),Leaf('t',3),List('e', 't'),6)))
  }

  test("decode and encode a very short text should be identity") {
    new TestTrees {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }

  test("get code bits for char") {
    val t: CodeTable = List(
      ('a', List(0, 0)),
      ('b', List(0, 1)),
      ('c', List(1, 0))
    )

    new TestTrees {
      assert(codeBits(t)('b') === List(0, 1))
    }
  }

  test("quick encode equals encode") {
    new TestTrees {
      assert(encode(t1)("ab".toList) === quickEncode(t1)("ab".toList))
    }
  }

}
