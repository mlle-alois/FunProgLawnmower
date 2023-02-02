package progfun.output

import play.api.libs.json.{JsValue, Json}
import progfun.{Grass, Lawnmower}

object JsonOutput {
  // serialize dynamique
   def serialize[A](value: A)(implicit serializer :  Output[A,JsValue]): JsValue = serializer.serialize(value)

  // On va rajouter des types qu'on puisse sérialiser
  implicit val grassSerializer: Output[Grass, JsValue] = {
    new Output[Grass,JsValue] {
      def serialize(grass: Grass): JsValue = {
        Json.obj( "limite" -> Json.obj(
          "x" -> grass.width
          ,
          "y" -> grass.height
         ))
      }
    }

  }

  implicit val lawnmowerSerializer: Output[Lawnmower, JsValue] = {
    new Output[Lawnmower,JsValue] {
      def serialize(lawnmower: Lawnmower): JsValue = {
        Json.obj( "lawnmower" -> Json.obj( "point" -> Json.obj(
          "x" -> lawnmower.currentPosition.coordinate.x
          ,
          "y" -> lawnmower.currentPosition.coordinate.y
        )
          ,
          "orientation" -> lawnmower.currentPosition.orientation
          ,
          "instructions" -> lawnmower.instructions.value
         ))
      }
    }

  }


}
