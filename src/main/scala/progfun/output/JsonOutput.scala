package progfun.output

import play.api.libs.json.{JsValue, Json}
import progfun.{Grass, Lawnmower}

object JsonOutput {
  // serialize dynamique
  def serialize[A, B](grass: A, lawnmowers: B)(
    implicit serializer: Output[A, B, JsValue]
  ): JsValue =
    serializer.serialize(grass, lawnmowers)

  implicit val lawnmowersSerializer
  : Output[Grass, List[Lawnmower], JsValue] = {
    (grass: Grass, lawnmowers: List[Lawnmower]) => {
      Json.obj(
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
    }
  }

}
