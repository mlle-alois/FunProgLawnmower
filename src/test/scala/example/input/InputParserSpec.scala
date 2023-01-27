package example.input
import org.scalatest.funsuite.AnyFunSuite
import progfun.input.{Input, InputParser}

class InputParserSpec extends AnyFunSuite {

  val inputParser = new InputParser
  val filename = "input-files/input.txt";
  val input: Input = inputParser.parse(filename)

  test("Throw an error for not existing file") {
    assertThrows[Exception] {
      inputParser.parse("invalid.txt")
    }
  }

  test("Throw an error for empty file") {
    assertThrows[Exception] {
      inputParser.parse("invalid-empty-input.txt")
    }
  }

  test("Throw an error for incorrect input") {
    assertThrows[Exception] {
      inputParser.parse("invalid-input.txt")
    }
  }

  test("Grass height is right") {
    assert(input.grass.height == 5)
  }

  test("Grass width is right") {
    assert(input.grass.width == 5)
  }

  test("Lawnmower n°1 coordinate x is right") {
    assert(input.lawnmowers(0).currentPosition.coordinate.x == 1)
  }

  test("Lawnmower n°1 coordinate y is right") {
    assert(input.lawnmowers(0).currentPosition.coordinate.y == 2)
  }

  test("Lawnmower n°1 orientation is right") {
    assert(input.lawnmowers(0).currentPosition.orientation.equals("N"))
  }

  test("Lawnmower n°1 instructions are right") {
    assert(input.lawnmowers(0).instructions.value.equals("GAGAGAGAA"))
  }

  test("Number of lawnmowers is right") {
    assert(input.lawnmowers.length == 3)
  }
}
