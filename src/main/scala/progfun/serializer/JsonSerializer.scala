package progfun.serializer

import play.api.libs.json.Json
import progfun.{Grass, Lawnmower}

object JsonSerializer {
  // serialize dynamique
  def serialize[A, B](grass: A, lawnmowers: B)(
    implicit serializer: OutputSerializer[A, B, String]
  ): String =
    serializer.serialize(grass, lawnmowers)

  implicit val lawnmowersSerializer: OutputSerializer[Grass, List[Lawnmower], String] = {
    (grass: Grass, lawnmowers: List[Lawnmower]) => {
      Json.prettyPrint(Json.obj(
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
      ))
    }
  }

}
