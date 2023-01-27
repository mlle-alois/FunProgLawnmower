package progfun.output

import progfun.{Grass, Lawnmower}

class JsonOutput(val grass: Grass, val lawnmowers: List[Lawnmower]) extends Output {
  def toJson: String = {
    ""
  }
}
