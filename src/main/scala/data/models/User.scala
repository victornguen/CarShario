package carshario
package data.models

import data.types._

import eu.timepit.refined.api.Refined
import eu.timepit.refined.auto._
import eu.timepit.refined.numeric.Positive

import java.time.LocalDate

case class User (id: Int,
                 firstName: HumanName,
                 lastName: HumanName,
                 birthday: Birthday,
                 drivingLicenseId: DrivingLicenseId,
                 drivingExp: HumanLiveYears
                )
object example {
  val u = User(11243, "Vic", "Nguen",
    LocalDate.of(1999, 5, 9),
  "A123-4567-8900",
    1)
}
