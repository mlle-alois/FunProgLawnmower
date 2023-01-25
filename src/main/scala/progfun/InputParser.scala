package progfun

import better.files._

class InputParser(val fileName: String) {

  def parse(): Input = {
    val file = File(fileName)
    val lines = file.lines.toList

    val grass = parseGrass(lines(0))
    val lawnmowers = parseLawnmowers(lines.drop(1))
    new Input(grass, lawnmowers)
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
      new Lawnmower(new Position(coordinate, orientation), instructions)
    }).toList
  }
}
