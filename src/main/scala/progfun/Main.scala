package progfun

import better.files.File
import com.typesafe.config.{Config, ConfigFactory}
import progfun.input.InputParser
import progfun.output.CSVOutput
import progfun.output.CSVOutput.lawnmowersSerializer

object Main extends App {

  val conf: Config = ConfigFactory.load()

  private val inputFileName = conf.getString("appplication.input-file")

  private val inputParser = new InputParser
  private val input = inputParser.parse(inputFileName)

  val lawnmowers = for (lawnmower <- input.lawnmowers) yield lawnmower.copy(finalPosition = lawnmower.followInstructions(input.grass))
  println(lawnmowers(0).finalPosition.coordinate.y)

  private val outputTypeChoice = conf.getString("appplication.output-type-choice")
  println(outputTypeChoice)
  outputTypeChoice match {
    case "json" => {
      //TODO déplacer ça dans une classe + cas de test
//      val outputFileName = conf.getString("appplication.output-json-file")
//      val jsonValue = JsonOutput.serialize(input.grass, lawnmowers)
//      val file = File(outputFileName)
//
//      file.createIfNotExists().overwrite(Json.prettyPrint(jsonValue))
      println("Successfully written to JSON file")
    }
    case "yaml" => {
      //val outputFileName = conf.getString("appplication.output-yaml-file")
      //val file = File(outputFileName)

      println("Successfully written to YAML file")
    }
    case "csv" => {
      val outputFileName = conf.getString("appplication.output-csv-file")
      val csvValue = CSVOutput.serialize(input.grass, lawnmowers)
      val file = File(outputFileName)

      file.createIfNotExists().overwrite(csvValue)
      println("Successfully written to CSV file")
    }
    case _ => println("Invalid output type choice")
  }
}
