package carshario
package dao.models

import java.time.LocalDateTime

case class RentedCar(
  carId: Int,
  userId: Int,
  timeFrom: LocalDateTime,
  timeTo: Option[LocalDateTime]
)
