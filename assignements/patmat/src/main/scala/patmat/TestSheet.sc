import patmat.Huffman.Leaf
import patmat._

Huffman.until(Huffman.singleton, Huffman.combine)(List(Leaf('e', 3), Leaf('t', 3), Leaf('x', 4), Leaf('y', 10)))