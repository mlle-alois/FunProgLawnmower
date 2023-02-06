package progfun.serializer

import progfun.{Grass, Lawnmower}

object YamlSerializer {
  // serialize dynamique
  def serialize[A, B](grass: A, lawnmowers: B)(
    implicit serializer: OutputSerializer[A, B, String]
  ): String =
    serializer.serialize(grass, lawnmowers)

  implicit val lawnmowersSerializer: OutputSerializer[Grass, List[Lawnmower], String] = {
    (grass: Grass, lawnmowers: List[Lawnmower]) => {
      "limite: \n"
        .concat("  x: ")
        .concat(grass.width.toString
        .concat("\n")
        .concat("  y: ")
        .concat(grass.height.toString)
        .concat("\n")
        .concat("tondeuses: \n")
        .concat(lawnmowers.map {
          lawnmower =>
            "  - debut: \n"
              .concat("      point: \n")
              .concat("        x: ")
              .concat(lawnmower.currentPosition.coordinate.x.toString)
              .concat("\n")
              .concat("        y: ")
              .concat(lawnmower.currentPosition.coordinate.y.toString)
              .concat("\n")
              .concat("      direction: ")
              .concat(lawnmower.currentPosition.orientation)
              .concat("\n")
              .concat("    instructions: ")
              .concat(lawnmower.instructions.value.split("").map(
                instruction => "\n      - ".concat(instruction)
              ).mkString)
              .concat("\n")
              .concat("    fin: \n")
              .concat("      point: \n")
              .concat("        x: ")
              .concat(lawnmower.finalPosition.coordinate.x.toString)
              .concat("\n")
              .concat("        y: ")
              .concat(lawnmower.finalPosition.coordinate.y.toString)
              .concat("\n")
              .concat("      direction: ")
              .concat(lawnmower.finalPosition.orientation)
        }.mkString("\n")))
    }
  }

}
