package example.serializer

import org.scalatest.funsuite.AnyFunSuite
import progfun._
import progfun.serializer.YamlSerializer
import progfun.serializer.YamlSerializer.lawnmowersSerializer

class YamlSerializerSpec extends AnyFunSuite {

  test("Should create a yaml object") {
    val grass: Grass = new Grass(5, 5)
    val lawnmower1 = Lawnmower(new Position(new Coordinate(1, 2), "N"), new Instructions("GAGAGAGAA"), new Position(new Coordinate(0, 0), "N"))
    val lawnmower2 = Lawnmower(new Position(new Coordinate(3, 3), "E"), new Instructions("AADAADADDA"), new Position(new Coordinate(0, 0), "N"))

    val lawnmowersList = List(lawnmower1, lawnmower2)
    val lawnmowers = for (lawnmower <- lawnmowersList) yield lawnmower.copy(finalPosition = lawnmower.followInstructions(grass))

    val result = "limite: \n" +
      "  x: 5\n" +
      "  y: 5\n" +
      "tondeuses: \n" +
      "  - debut: \n" +
      "      point: \n" +
      "        x: 1\n" +
      "        y: 2\n" +
      "      direction: N\n" +
      "    instructions: \n" +
      "      - G\n" +
      "      - A\n" +
      "      - G\n" +
      "      - A\n" +
      "      - G\n" +
      "      - A\n" +
      "      - G\n" +
      "      - A\n" +
      "      - A\n" +
      "    fin: \n" +
      "      point: \n" +
      "        x: 1\n" +
      "        y: 3\n" +
      "      direction: N\n" +
      "  - debut: \n" +
      "      point: \n" +
      "        x: 3\n" +
      "        y: 3\n" +
      "      direction: E\n" +
      "    instructions: \n" +
      "      - A\n" +
      "      - A\n" +
      "      - D\n" +
      "      - A\n" +
      "      - A\n" +
      "      - D\n" +
      "      - A\n" +
      "      - D\n" +
      "      - D\n" +
      "      - A\n" +
      "    fin: \n" +
      "      point: \n" +
      "        x: 5\n" +
      "        y: 1\n" +
      "      direction: E"

    val csvValue = YamlSerializer.serialize(grass, lawnmowers)

    assert(csvValue === result)
  }


}
