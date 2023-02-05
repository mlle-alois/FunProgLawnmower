package progfun

import progfun.input.InputParser
import com.typesafe.config.{Config, ConfigFactory}

object Main extends App {

  val conf: Config = ConfigFactory.load()

  private val inputFile = conf.getString("appplication.input-file")

  private val inputParser = new InputParser
  private val input = inputParser.parse(inputFile)

  //val lawnmowers = collection.mutable.Map[Lawnmower, Position]()

  val lawnmowers = for (lawnmower <- input.lawnmowers) yield lawnmower.copy(finalPosition = lawnmower.followInstructions(input.grass))
  println(lawnmowers(0).finalPosition.coordinate.y)

  private val outputTypeChoice = conf.getString("appplication.output-type-choice")
  outputTypeChoice match {
    case "json" => {
      // val jsonOutput = new JsonOutput(input.grass, lawnmowers.toMap)
    }
    case "yaml" => {
    }
    case "csv" => {
    }
    case _ => println("Invalid output type choice")
  }
}
