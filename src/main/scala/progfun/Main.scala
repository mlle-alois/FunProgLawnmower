package progfun

import com.typesafe.config.{Config, ConfigFactory}
import progfun.input.InputParser
import progfun.output.FileGenerator
import progfun.output.FileGenerator.jsonFileGenerator
import progfun.serializer.{CSVSerializer, JsonSerializer}

object Main extends App {

  val conf: Config = ConfigFactory.load()

  private val inputFileName = conf.getString("appplication.input-file")

  private val inputParser = new InputParser
  private val input = inputParser.parse(inputFileName)

  val lawnmowers = for (lawnmower <- input.lawnmowers) yield lawnmower.copy(finalPosition = lawnmower.followInstructions(input.grass))

  private val outputTypeChoice = conf.getString("appplication.output-type-choice")
  val outputValue: String = outputTypeChoice match {
    case "json" => {
      import progfun.serializer.JsonSerializer.lawnmowersSerializer
      JsonSerializer.serialize(input.grass, lawnmowers)
    }
    case "yaml" => {
//      import progfun.serializer.YamlSerializer.lawnmowersSerializer
//      YamlSerializer.serialize(input.grass, lawnmowers)
      ""
    }
    case "csv" => {
      import progfun.serializer.CSVSerializer.lawnmowersSerializer
      CSVSerializer.serialize(input.grass, lawnmowers)
    }
    case _ => {
      println("Invalid output type choice")
      ""
    }
  }
  //TODO cas de test
  val file = FileGenerator.generateFile(outputTypeChoice, outputValue)
}
