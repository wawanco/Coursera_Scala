trait Planar {
  def height: Int
  def width: Int
  def surface = height * width
}
abstract class Shape {}

trait Movable {}

// val in constructor is equivalent to overriding
// parameters
class Square(val height: Int, val width: Int) extends Shape with Planar with Movable {
}