package progfun

class Grass(val width: Int, val height: Int) {
  def isInside(coordinate: Coordinate): Boolean = {
    coordinate.x >= 0 && coordinate.x <= width && coordinate.y >= 0 && coordinate.y <= height
  }
}
