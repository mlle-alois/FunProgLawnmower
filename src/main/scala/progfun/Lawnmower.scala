package progfun

case class Lawnmower(currentPosition: Position, instructions: Instructions, finalPosition: Position) {
  def followInstructions(grass: Grass): Position = {
    instructions.value.foldLeft(currentPosition) { (position, instruction) =>
      instruction match {
        case 'G' => turnLeft(position)
        case 'D' => turnRight(position)
        case _ => move(grass, position)
      }
    }
  }

  private def move(grass: Grass, position: Position): Position = {
    val newCoordinate = position.orientation match {
      case "E" => new Coordinate(position.coordinate.x + 1, position.coordinate.y)
      case "S" => new Coordinate(position.coordinate.x, position.coordinate.y - 1)
      case "W" => new Coordinate(position.coordinate.x - 1, position.coordinate.y)
      case _ => new Coordinate(position.coordinate.x, position.coordinate.y + 1)
    }
    if (grass.isInside(newCoordinate)) {
      new Position(newCoordinate, position.orientation)
    } else {
      position
    }
  }

  private def turnLeft(position: Position): Position = {
    val newOrientation = position.orientation match {
      case "W" => "S"
      case "S" => "E"
      case "E" => "N"
      case _ => "W"
    }
    new Position(position.coordinate, newOrientation)
  }

  private def turnRight(position: Position): Position = {
    val newOrientation = position.orientation match {
      case "E" => "S"
      case "S" => "W"
      case "W" => "N"
      case _ => "E"
    }
    new Position(position.coordinate, newOrientation)
  }

}
