package progfun

object Main extends App {
  val grass = new Grass(5, 5)
  val lawnmowers = List(
    new Lawnmower(new Position(new Coordinate(1, 2), "N"), new Instructions("GAGAGAGAA")),
    new Lawnmower(new Position(new Coordinate(3, 3), "E"), new Instructions("AADAADADDA"))
  )
  lawnmowers.foreach(lawnmower => {
    val finalPosition = lawnmower.followInstructions(grass)
    println(finalPosition.coordinate.x)
    println(finalPosition.coordinate.y)
    println(finalPosition.orientation)
  })
}
