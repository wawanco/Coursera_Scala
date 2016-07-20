import forcomp.Anagrams._

wordOccurrences("scala")

List("scala", "is", "yala").reduce(_ ++ _)

wordOccurrences(List("scala", "is", "yala").reduce(_ ++ _))

//dictionary map (w => (wordOccurrences(w), w)) groupBy (_._1) mapValues(lst => lst.head._2)

val a = List(('a', 2), ('b', 2))

def combinations(occurrences: Occurrences): List[Occurrences] = {
  if(occurrences.isEmpty) List(List())
  else {
    val firstElem: (Char, Int) = occurrences.head
    for {
      fstOcc <- 0 to firstElem._2
      rest <- combinations(occurrences.tail)
    } yield {
      if(fstOcc == 0) rest
      else (firstElem._1, fstOcc) :: rest
    }
  }.toList
}

//combinations(a)

val lard = List(('a', 2), ('d', 1), ('l', 1), ('r', 1))
val r = List(('a', 1), ('r', 1))
val lad = List(('a', 1), ('d', 1), ('l', 1))

// r.toMap foldLeft (lard.toMap) ((res, elem) => res + elem )
r.toMap.foldLeft(lard.toMap)((res:Map[Char, Int], elem) => {
  val (subChar, subFreq) = elem
  val newFreq = res(subChar) - subFreq
  if(newFreq == 0) res - subChar
  else res + (subChar -> newFreq)
})

combinations(sentenceOccurrences(List("null"))).tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.tail.head
(dictionaryByOccurrences withDefaultValue Nil)(List(('l',2), ('n',1), ('u',1))).head
combinations(subtract(sentenceOccurrences(List("null")), List(('l',2), ('n',1), ('u',1))))
