package carshario
package data

import eu.timepit.refined.api.Refined
import eu.timepit.refined.numeric.Greater
import eu.timepit.refined.numeric.Interval.Closed
import eu.timepit.refined.string.MatchesRegex

import java.time.LocalDate

package object types {
val str = "ddd"
  type Color       = String Refined MatchesRegex["^[A-Za-z]+$"]
  type VIN         = String Refined MatchesRegex["^(?=.*[0-9])(?=.*[A-z])[0-9A-z-]{17}$"]
  type CarProducer = String Refined MatchesRegex["^[A-Za-z\\s\\-0-9]+$"]
  type CarModel    = String Refined MatchesRegex["^[A-Za-z\\s\\-0-9]+$"]
  type ProdYear    = Int Refined Greater[1886]
  //latitude, longitude
  type Coords = (Double Refined Closed[-90.0, 90.0], Double Refined Closed[-180.0, 180.0])

  type HumanName        = String Refined MatchesRegex["^[А-Яа-яA-Za-z\\s]+$"]
  type Age              = Int Refined eu.timepit.refined.numeric.Interval.Closed[18, 150]
  type Birthday         = java.time.LocalDate
  type DrivingLicenseId = String Refined MatchesRegex["^[A-Z](?:[-\\d]*){14}$"]
  type HumanLiveYears = Int Refined Closed[0, 150] // let's suppose that human live less than 150 years

  type Latitude  = Double Refined Closed[-90.0, 90.0]
  type Longitude = Double Refined Closed[-180.0, 180.0]
}
