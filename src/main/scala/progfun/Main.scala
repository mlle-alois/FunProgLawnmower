package progfun

import better.files.File
import com.typesafe.config.{Config, ConfigFactory}
import progfun.input.InputParser
import progfun.output.JsonOutput
import progfun.output.JsonOutput.lawnmowersSerializer

object Main extends App {

  val conf: Config = ConfigFactory.load()

  private val inputFileName = conf.getString("appplication.input-file")

  private val inputParser = new InputParser
  private val input = inputParser.parse(inputFileName)

  //val lawnmowers = collection.mutable.Map[Lawnmower, Position]()

  val lawnmowers = for (lawnmower <- input.lawnmowers) yield lawnmower.copy(finalPosition = lawnmower.followInstructions(input.grass))
  println(lawnmowers(0).finalPosition.coordinate.y)

  private val outputTypeChoice = conf.getString("appplication.output-type-choice")
  outputTypeChoice match {
    case "json" => {
      val ouputFileName = conf.getString("appplication.output-json-file")
      val jsonValue = JsonOutput.serialize(input.grass, lawnmowers)
      val f = File(ouputFileName)

      f.createIfNotExists().appendText(jsonValue.toString())
      println("Successfully written to JSON file")
    }
    case "yaml" => {
      val ouputFileName = conf.getString("appplication.output-json-file")
      val jsonValue = JsonOutput.serialize(input.grass, lawnmowers)
      val f = File(ouputFileName)

      f.createIfNotExists().overwrite(jsonValue.toString)
      println("Successfully written to YAML file")
    }
    case "csv" => {
      val ouputFileName = conf.getString("appplication.output-json-file")
      val jsonValue = JsonOutput.serialize(input.grass, lawnmowers)
      val f = File(ouputFileName)

      f.createIfNotExists().overwrite(jsonValue.toString)
      println("Successfully written to CSV file")
    }
    case _ => println("Invalid output type choice")
  }
}
