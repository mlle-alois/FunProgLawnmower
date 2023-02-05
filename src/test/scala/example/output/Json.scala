package example.output

import org.scalactic.TypeCheckedTripleEquals.convertToCheckingEqualizer
import org.scalatest.funsuite.AnyFunSuite
import play.api.libs.json
import play.api.libs.json.Json
import progfun.{Coordinate, Grass, Instructions, Lawnmower, Position}
import progfun.output.JsonOutput
import progfun.output.JsonOutput.{grassSerializer}

class Json extends AnyFunSuite {

  test("Should create a json grass object") {
    val grass: Grass = new Grass(5, 5)
    val lawnmower1 = new Lawnmower(new Position(new Coordinate(1, 2), "N"), new Instructions("GAGAGAGAA"))
    val lawnmower2 = new Lawnmower(new Position(new Coordinate(3, 3), "E"), new Instructions("AADAADADDA"))
    val result =         Json.obj( "limite" -> Json.obj(
      "x" -> 5
      ,
      "y" -> 5
    ))

    assert( json === result)
  }


}
