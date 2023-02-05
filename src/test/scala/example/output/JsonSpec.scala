package example.output

import org.scalatest.funsuite.AnyFunSuite
import play.api.libs.json.Json
import progfun.serializer.JsonSerializer.lawnmowersSerializer
import progfun.serializer.JsonSerializer
import progfun.{Coordinate, Grass, Instructions, Lawnmower, Position}

class JsonSpec extends AnyFunSuite {

  test("Should create a json grass object") {
    val grass: Grass = new Grass(5, 5)
    val lawnmower1 = Lawnmower(new Position(new Coordinate(1, 2), "N"), new Instructions("GAGAGAGAA"), new Position(new Coordinate(0, 0), "N"))
    val lawnmower2 = Lawnmower(new Position(new Coordinate(3, 3), "E"), new Instructions("AADAADADDA"), new Position(new Coordinate(0, 0), "N"))

    val lawnmowersList = List(lawnmower1, lawnmower2)
    val lawnmowers = for (lawnmower <- lawnmowersList) yield lawnmower.copy(finalPosition = lawnmower.followInstructions(grass))

    val result = Json.obj(
      "limite" -> Json.obj(
        "x" -> grass.width,
        "y" -> grass.height
      ),
      "tondeuses" ->
        lawnmowers.map {
          lawnmower =>
            Json.obj(
              "debut" -> Json.obj(
                "point" -> Json.obj(
                  "x" -> lawnmower.currentPosition.coordinate.x,
                  "y" -> lawnmower.currentPosition.coordinate.y
                ),
                "direction" -> lawnmower.currentPosition.orientation
              ),
              "instructions" -> lawnmower.instructions.value.split(""),
              "fin" -> Json.obj(
                "point" -> Json.obj(
                  "x" -> lawnmower.finalPosition.coordinate.x,
                  "y" -> lawnmower.finalPosition.coordinate.y
                ),
                "direction" -> lawnmower.finalPosition.orientation
              )
            )
        }
    )

    val jsonValue = JsonSerializer.serialize(grass, lawnmowers)

    assert(jsonValue === result)
  }


}
