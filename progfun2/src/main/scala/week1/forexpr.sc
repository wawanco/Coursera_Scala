case class Book(title: String, authors: List[String])

val books: Set[Book] = Set(
  Book(title  = "Book 1",
       authors = List("Abelson, Harald", "Sussman, Gerald")
  ),
  Book(title  = "Book 2",
    authors = List("Bird, Richard", "Walder, Phil")
  ),
  Book(title  = "Book 3",
    authors = List("Bloch, Joashua")
  ),
  Book(title  = "Book 4",
    authors = List("Gafter, Neal", "Bloch, Joashua")
  ),
  Book(title  = "Programming in Scala",
    authors = List("Odersky, Martin", "Spoon, Lex")
  ),
  Book(title  = "Book 5",
    authors = List("Bloch, Joashua")
  )
)

// Title of books whose author's name is Bloch
for(b <- books; a <- b.authors; if a startsWith "Bloch")
  yield b.title


books flatMap (
  b => b.authors withFilter(a => a startsWith "Bloch") map (y => b.title)
)

// Books containg Program in the title
for(b <- books; if (b.title indexOf "Program") >= 0)
  yield b.title

// Find the names of all authors who have written at least two books
for{
  b1 <- books
  b2 <- books
  if b1.title > b2.title
  a1 <- b1.authors
  a2 <- b2.authors
  if a1 == a2
} yield a1