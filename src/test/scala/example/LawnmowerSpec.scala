package example

import org.scalatest.funsuite.AnyFunSuite
import progfun.{Coordinate, Grass, Instructions, Lawnmower, Position}

class LawnmowerSpec extends AnyFunSuite {

  val grass = new Grass(5, 5)
  val lawnmower1 = new Lawnmower(new Position(new Coordinate(1, 2), "N"), new Instructions("GAGAGAGAA"))
  val lawnmower2 = new Lawnmower(new Position(new Coordinate(3, 3), "E"), new Instructions("AADAADADDA"))

  test("Lawnmower 1 final position is right") {
    val finalPosition = lawnmower1.followInstructions(grass)
    val testedPosition = new Position(new Coordinate(1, 3), "N")
    assert(finalPosition.coordinate.x == testedPosition.coordinate.x)
    assert(finalPosition.coordinate.y == testedPosition.coordinate.y)
    assert(finalPosition.orientation == testedPosition.orientation)
  }

  test("Lawnmower 2 final position is right") {
    val finalPosition = lawnmower2.followInstructions(grass)
    val testedPosition = new Position(new Coordinate(5, 1), "E")
    assert(finalPosition.coordinate.x == testedPosition.coordinate.x)
    assert(finalPosition.coordinate.y == testedPosition.coordinate.y)
    assert(finalPosition.orientation == testedPosition.orientation)
  }

}
