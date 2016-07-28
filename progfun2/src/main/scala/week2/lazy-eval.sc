// Problem with stream: if tail is called several times,
// the corresponded stream will be recomputed each time
// => we have to store the result once computed (ok because
// pure functional programming)

// Strict eval => always computed
// Lazy eval => computed, on-demand + store result
// call-by-name => computed each time it is demanded

def expr = {
  // strict
  val x = {print("x"); 1}
  // lazy
  lazy val y = {print("y"); 2}
  // by-name
  def z = {print("z"); 3}
  z + y + x + z + y + x
}

expr
