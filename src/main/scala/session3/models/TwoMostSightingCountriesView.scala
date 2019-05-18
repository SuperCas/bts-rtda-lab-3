package session3.models

case class TwoMostSightingCountriesView(countries: String) extends View {

  override def toString: String = {
    s"Two Most Sighting Countries: $countries"
  }

  override def name(): String = "two_most_sighting_countries_view"
}
