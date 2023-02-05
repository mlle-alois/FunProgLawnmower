package progfun

import progfun.input.InputParser
import com.typesafe.config.{Config, ConfigFactory}

object Main extends App {

  val conf: Config = ConfigFactory.load()

  private val inputFile = conf.getString("appplication.input-file")

  private val inputParser = new InputParser
  private val input = inputParser.parse(inputFile)
  val lawnmowers = collection.mutable.Map[Lawnmower, Position]()
  input.lawnmowers.foreach(lawnmower => {
    val finalPosition = lawnmower.followInstructions(input.grass)
    val added = lawnmowers.put(lawnmower, finalPosition)
    added match {
      case Some(_) =>
        println("Already exists")
      case None =>
        println("Successfully added")
    }
    println(finalPosition.coordinate.x)
    println(finalPosition.coordinate.y)
    println(finalPosition.orientation)

  })

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
