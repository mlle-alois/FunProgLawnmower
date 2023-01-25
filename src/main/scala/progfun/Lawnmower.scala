package progfun

class Lawnmower(val currentPosition: Position, val instructions: Instructions) {
  def followInstructions(grass: Grass): Position = {
    instructions.value.foldLeft(currentPosition) { (position, instruction) =>
      instruction match {
        case 'A' => move(grass, position)
        case 'G' => turnLeft(position)
        case 'D' => turnRight(position)
      }
    }
  }

  private def move(grass: Grass, position: Position): Position = {
    val newCoordinate = position.orientation match {
      case "N" => new Coordinate(position.coordinate.x, position.coordinate.y + 1)
      case "E" => new Coordinate(position.coordinate.x + 1, position.coordinate.y)
      case "S" => new Coordinate(position.coordinate.x, position.coordinate.y - 1)
      case "W" => new Coordinate(position.coordinate.x - 1, position.coordinate.y)
    }
    if(grass.isInside(newCoordinate)) {
      new Position(newCoordinate, position.orientation)
    } else {
      position
    }
  }

  private def turnLeft(position: Position): Position = {
    val newOrientation = position.orientation match {
      case "N" => "W"
      case "W" => "S"
      case "S" => "E"
      case "E" => "N"
    }
    new Position(position.coordinate, newOrientation)
  }

  private def turnRight(position: Position): Position = {
    val newOrientation = position.orientation match {
      case "N" => "E"
      case "E" => "S"
      case "S" => "W"
      case "W" => "N"
    }
    new Position(position.coordinate, newOrientation)
  }
}
