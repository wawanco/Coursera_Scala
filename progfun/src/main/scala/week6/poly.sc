class Poly(val terms0: Map[Int, Double]){

  // Varying number of parameters using repeated parameters
  def this(bindings: (Int, Double)*) = this(bindings.toMap)

  val terms = terms0 withDefaultValue 0.0

  private def adjust(term: (Int, Double)): (Int, Double) = {
    val (exp, coeff) = term
    exp -> (terms(exp) + coeff)
    // If we don't use withDefaultValue transformation
    //terms get exp match {
    //  case None => term
    //  case Some(x) => exp -> (x + coeff)
    //}
  }

  def addTerm(terms: Map[Int, Double],  term:(Int, Double)): Map[Int, Double] ={
    val (exp, coeff) = term
    terms + (exp -> (terms(exp) + coeff))
  }

  // == ++ Version
  // def + (other: Poly) = new Poly(terms ++ (other.terms map adjust))
  // == foldLeft version
  def + (other: Poly) = new Poly((other.terms foldLeft terms)(addTerm))

  override def toString =
    (for((k, v) <- terms.toList.sorted) yield
          if(k == 0) v
          else if(k == 1) v + " x"
          else v + " x^" + k) mkString(" + ")
}

val p1 = new Poly(1 -> 2.0, 3 -> 4.0, 5 -> 6.2)
val p2 = new Poly(Map(0 -> 3.0, 3 -> 7.0))
p1

p1 + p2
