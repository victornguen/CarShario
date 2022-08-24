package carshario
package dao.models

import dao.types.{Latitude, Longitude}
import java.time.LocalDateTime

case class CarState(
    carId: Int,
    timestamp: LocalDateTime,
    description: String,
    latitude: Latitude,
    longitude: Longitude
)
