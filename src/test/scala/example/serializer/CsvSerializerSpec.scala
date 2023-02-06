package example.serializer

import org.scalatest.funsuite.AnyFunSuite
import progfun._
import progfun.serializer.CsvSerializer
import progfun.serializer.CsvSerializer.lawnmowersSerializer

class CsvSerializerSpec extends AnyFunSuite {

  test("Should create a csv object") {
    val grass: Grass = new Grass(5, 5)
    val lawnmower1 = Lawnmower(new Position(new Coordinate(1, 2), "N"), new Instructions("GAGAGAGAA"), new Position(new Coordinate(0, 0), "N"))
    val lawnmower2 = Lawnmower(new Position(new Coordinate(3, 3), "E"), new Instructions("AADAADADDA"), new Position(new Coordinate(0, 0), "N"))

    val lawnmowersList = List(lawnmower1, lawnmower2)
    val lawnmowers = for (lawnmower <- lawnmowersList) yield lawnmower.copy(finalPosition = lawnmower.followInstructions(grass))

    val result = "numéro;taille_x;taille_y;début_x;début_y;début_direction;fin_x;fin_y;fin_direction;instructions;\n" +
      "1;5;5;1;2;N;1;3;N;GAGAGAGAA;\n" +
      "2;5;5;3;3;E;5;1;E;AADAADADDA;\n"

    val csvValue = CsvSerializer.serialize(grass, lawnmowers)

    assert(csvValue === result)
  }


}
