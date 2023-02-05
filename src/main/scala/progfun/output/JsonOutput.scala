package progfun.output

import play.api.libs.json.{JsValue, Json}
import progfun.{Grass, Lawnmower, Position}

object JsonOutput {
  // serialize dynamique
  def serialize[A, B](grass: A, lawnmowers: B)(
      implicit serializer: Output[A, B, JsValue]
  ): JsValue =
    serializer.serialize(grass, lawnmowers)

  // On va rajouter des types qu'on puisse sérialiser
  implicit val grassSerializer
      : Output[Grass, Map[Lawnmower, Position], JsValue] = {
    new Output[Grass, Map[Lawnmower, Position], JsValue] {
      def serialize(
          grass: Grass,
          lawnmowers: Map[Lawnmower, Position]
      ): JsValue = {
        Json.obj(
          "limite" -> Json.obj(
            "x" -> grass.width,
            "y" -> grass.height
          ),
          "tondeuses" -> Json.arr(
            lawnmowers.map {
              case (lawnmower, position) =>
                Json.obj(
                  "debut" -> Json.obj(
                    "point" -> Json.obj(
                      "x" -> position.coordinate.x,
                      "y" -> position.coordinate.y
                    ),
                    "direction" -> position.orientation
                  ),
                  "instructions" -> lawnmower.instructions.value
                )
            }
          )
        )
      }

    }

  }

  /*implicit val lawnmowerSerializer
      : Output[Grass, Map[Lawnmower, Position], JsValue] = {
    new Output[Lawnmower, JsValue] {
      def serialize(lawnmower: Lawnmower, grass: Grass): JsValue = {
        Json.obj(
          "tondeuses" -> Json.arr(
            "debut" -> Json.obj(
              "point" -> Json.obj(
                "x" -> lawnmower.currentPosition.coordinate.x,
                "y" -> lawnmower.currentPosition.coordinate.y
              ),
              "direction" -> lawnmower.currentPosition.orientation
            ),
            "instructions" -> lawnmower.instructions.value
          )
        )
      }

    }

  }*/

}
