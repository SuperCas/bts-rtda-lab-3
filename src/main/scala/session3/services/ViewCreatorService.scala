package session3.services

import session3.models.{MostCommonBirdView, Sighting, TotalAmountView, TwoMostSightingCountriesView, UniquesBirdTypeDetectedView}

object ViewCreatorService {
  def createTotalAmountView(inputSightingSeq: Seq[Sighting]): TotalAmountView = {
    TotalAmountView(inputSightingSeq.size)
  }

  def createUniquesBirdTypeDetectedView(inputSightingSeq: Seq[Sighting]): UniquesBirdTypeDetectedView = {
    var uniques: Set[Int] = Set()
    for (sighting <- inputSightingSeq) {
      if (!uniques.contains(sighting.birdTypeId)) {
        uniques += sighting.birdTypeId
      }
    }
    UniquesBirdTypeDetectedView(uniques.size)
  }

  def createMostCommonBirdView(inputSightingSeq: Seq[Sighting]): MostCommonBirdView = {
    val groupsByBirdTypeId: Map[Int, Seq[Sighting]] = inputSightingSeq.groupBy(sighting => sighting.birdTypeId)
    val uniquesCount: Map[Int, Int] = groupsByBirdTypeId.map(par => (par._1, par._2.size))
    val max = uniquesCount.maxBy(par => par._2)
    MostCommonBirdView(max._1, max._2)
  }

  def createTwoMostSightingCountries(inputSightingSeq: Seq[Sighting]) : TwoMostSightingCountriesView = {
    TwoMostSightingCountriesView(inputSightingSeq.map(line => (line.countryName, 1)).groupBy(_._1).map(line => (line._1, line._2.size)).toSeq.sortBy(-_._2).map(_._1).slice(0,2).mkString(sep = ", "))
  }
}
