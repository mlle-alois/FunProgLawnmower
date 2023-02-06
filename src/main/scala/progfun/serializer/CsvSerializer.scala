package progfun.serializer

import progfun.{Grass, Lawnmower}

object CsvSerializer {
  // serialize dynamique
  def serialize[A, B](grass: A, lawnmowers: B)(
    implicit serializer: OutputSerializer[A, B, String]
  ): String =
    serializer.serialize(grass, lawnmowers)

  implicit val lawnmowersSerializer
  : OutputSerializer[Grass, List[Lawnmower], String] = {
    (grass: Grass, lawnmowers: List[Lawnmower]) => {
      List("numéro;",
        "taille_x;",
        "taille_y;",
        "début_x;",
        "début_y;",
        "début_direction;",
        "fin_x;",
        "fin_y;",
        "fin_direction;",
        "instructions\n",
        lawnmowers.zipWithIndex.map {
          case (lawnmower, index) =>
            List((index + 1).toString,
                  grass.width.toString,
                  grass.height.toString,
                  lawnmower.currentPosition.coordinate.x.toString,
                  lawnmower.currentPosition.coordinate.y.toString,
                  lawnmower.currentPosition.orientation,
                  lawnmower.finalPosition.coordinate.x.toString,
                  lawnmower.finalPosition.coordinate.y.toString,
                  lawnmower.finalPosition.orientation,
                  lawnmower.instructions.value)
              .mkString(";")
      }.mkString("\n")).mkString("")
    }
  }

}
