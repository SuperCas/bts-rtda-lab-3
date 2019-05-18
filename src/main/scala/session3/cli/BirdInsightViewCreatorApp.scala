package session3.cli

import session3.models.{Sighting, View}
import session3.services.{ConsoleViewWriterService, FileViewWriterService, TransformService, ViewCreatorService, ViewWriter}

import scala.io.Source

object BirdInsightViewCreatorApp {
  var inputFileName: String = "sightings.txt"
  var outputTo: String = "console"

  def main(args: Array[String]): Unit = {
    if(args.size > 0)
     inputFileName = args(0)

    if(args.size > 1)
      outputTo = "file"

    val lines = Source.fromFile(inputFileName).getLines().toSeq
    val sightings: Seq[Sighting] = TransformService.execute(lines)

    var views: List[View] = List[View]()
    views ::= ViewCreatorService.createTotalAmountView(sightings)
    views ::= ViewCreatorService.createUniquesBirdTypeDetectedView(sightings)
    views ::= ViewCreatorService.createMostCommonBirdView(sightings)
    views ::= ViewCreatorService.createTwoMostSightingCountries(sightings)

    var writer: ViewWriter = null

    if(outputTo.equals("console"))
      writer = new ConsoleViewWriterService()
    else
      writer = new FileViewWriterService()

    for (view <- views) {
      writer.write(view)
    }
  }
}
