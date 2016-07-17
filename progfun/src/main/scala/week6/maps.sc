val roman = Map("I" -> 1, "II" -> 2, "V" -> 5)
val capital = Map("US" -> "Washington", "France" -> "Paris")

// Map as function
roman("I")
capital apply "US"

try {
  capital("Andorra")
} catch {
  case e => println(e)
}


// Access value if you without knowing whether key exists or not
capital get "Andorra"
capital get "US"

// Pattern match on Option
def showCapital(country: String): String = capital.get(country) match {
  case None => "I don't know"
  case Some(x) => x
}

showCapital("France")
showCapital("Spain")


// Order By
val fruits = List("banana", "orange", "pineapple", "apricot", "pear")
fruits.sortWith((x, y) => x.length < y.length)
fruits.sortWith(_.length < _.length)
fruits.sorted

// Group
fruits.groupBy(_.head)

fruits.groupBy(_.length)

//Turns map into total function
val cap1 = capital withDefaultValue "<unknown>"
cap1("Spain")

val c2 = capital + ("Spain" -> "Madrid")
c2

c2 + ("Spain" -> "Barcelona")