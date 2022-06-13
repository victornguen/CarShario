package carshario
package data.models

import data.types._

import java.time.LocalDate

case class User(
  id: Option[Int] = Some(0),
  firstName: HumanName,
  lastName: HumanName,
  birthday: Birthday,
  drivingLicenseId: DrivingLicenseId,
  drivingStartDate: LocalDate
)
