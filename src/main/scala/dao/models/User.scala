package carshario
package dao.models

import dao.types._

import java.time.LocalDate

case class User(
  id: Option[Int] = Some(0),
  firstName: HumanName,
  lastName: HumanName,
  birthday: Birthday,
  drivingLicenseId: DrivingLicenseId,
  drivingStartDate: LocalDate
)
