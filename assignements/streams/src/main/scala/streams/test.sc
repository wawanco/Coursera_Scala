import streams.{GameDef, Solver, StringParserTerrain}

val v = Vector(Vector('S', 'T'), Vector('o', 'o'), Vector('o', 'o'))
v(0)(1) != '-'

class testLevel extends GameDef with Solver with StringParserTerrain {
  val level =
    """ooo-------
      |oSoooo----
      |ooooooooo-
      |-ooooooooo
      |-----ooToo
      |------ooo-""".stripMargin
}

val level = new testLevel()

level.pathsFromStart filter (path => level.done(path._1)) take 1 toList