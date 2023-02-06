package progfun

import com.typesafe.config.{Config, ConfigFactory}
import progfun.input.InputParser
import progfun.output.FileGenerator
import progfun.output.FileGenerator.jsonFileGenerator
import progfun.serializer.{CsvSerializer, JsonSerializer, YamlSerializer}

object Main extends App {

  val conf: Config = ConfigFactory.load()

  private val inputParser = new InputParser
  private val input = inputParser.parse(conf.getString("appplication.input-file"))

  val lawnmowers = for (lawnmower <- input.lawnmowers)
    yield lawnmower.copy(finalPosition = lawnmower.followInstructions(input.grass))

  private val outputTypeChoice = conf.getString("appplication.output-type-choice")
  private val outputValue: String = outputTypeChoice match {
    case "json" =>
      import progfun.serializer.JsonSerializer.lawnmowersSerializer
      JsonSerializer.serialize(input.grass, lawnmowers)
    case "yaml" =>
      import progfun.serializer.YamlSerializer.lawnmowersSerializer
      YamlSerializer.serialize(input.grass, lawnmowers)
    case "csv" => {
      import progfun.serializer.CsvSerializer.lawnmowersSerializer
      CsvSerializer.serialize(input.grass, lawnmowers)
    }
    case _ =>
      println("Invalid output type choice")
      ""
  }

  val file = FileGenerator.generateFile(outputTypeChoice, outputValue)
}
