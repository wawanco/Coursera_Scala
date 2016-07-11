import patmat.Huffman._
import patmat._

val l = List(Leaf('e', 3), Leaf('t', 3), Leaf('x', 4), Leaf('y', 10))

combine(l)

until(x => singleton(x), x => combine(x))(l)
/*
Huffman.until(
  x:List[CodeTree] => singleton(x),
  x:List[CodeTree] => combine(x))
(List(Leaf('e', 3), Leaf('t', 3), Leaf('x', 4), Leaf('y', 10))*/

secret.length

decodedSecret

val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
encode(t1)("ab".toList)