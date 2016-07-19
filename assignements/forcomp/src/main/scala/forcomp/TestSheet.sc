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

val lard = List(('a', 1), ('d', 1), ('l', 1), ('r', 1))
val r = List(('r', 1))
val lad = List(('a', 1), ('d', 1), ('l', 1))


lard.toMap