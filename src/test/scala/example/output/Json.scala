package example.output

import org.scalatest.funsuite.AnyFunSuite
import play.api.libs.json.Json
import progfun.Grass
import progfun.output.JsonOutput
import progfun.output.JsonOutput.grassSerializer

class Json extends AnyFunSuite {

  test("Should create a json grass string") {
    val grass: Grass = new Grass(5, 5)
    val json = JsonOutput.serialize(grass)
    val result =         Json.obj( "limite" -> Json.obj(
      "x" -> 5
      ,
      "y" -> 5
    ))

    assert( json === result)
  }
  test("Should create a json  string") {
    val grass: Grass = new Grass(5, 5)
    val json = JsonOutput.serialize(grass)

    assert( json.toString() === "{\"width\":5,\"height\":5}")
  }

}
