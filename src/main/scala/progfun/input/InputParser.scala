package progfun.input

import better.files._
import progfun._
import progfun.exception.IncorrectDataException

class InputParser() {

  //TODO check que la tondeuse est bien dans la pelouse
  //TODO interaction avec les fichiers en dehors du parser
  @SuppressWarnings(Array("org.wartremover.warts.Throw"))
  def parse(fileName: String): Input = {
    try {
      val file = File(fileName)
      val lines = file.lines.toList

      val grass = parseGrass(lines(0))
      val lawnmowers = parseLawnmowers(lines.drop(1))
      new Input(grass, lawnmowers)
    } catch {
      case e: Exception => throw IncorrectDataException("No file or incorrect data in file: " + fileName + " " + e.getMessage)
    }
  }

  private def parseGrass(str: String): Grass = {
    val coordinates = str.split(" ")
    new Grass(coordinates(0).toInt, coordinates(1).toInt)
  }

  private def parseLawnmowers(lines: List[String]): List[Lawnmower] = {
    lines.grouped(2).map(lawnmower => {
      val coordinates = lawnmower(0).split(" ")
      val coordinate = new Coordinate(coordinates(0).toInt, coordinates(1).toInt)
      val orientation = coordinates(2)
      val instructions = new Instructions(lawnmower(1))
      new Lawnmower(new Position(coordinate, orientation), instructions, new Position(new Coordinate(0, 0), "N"))
    }).toList
  }
}
