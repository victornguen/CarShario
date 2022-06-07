package carshario
package data.models

import data.types._

import java.sql.Timestamp
import java.time.LocalDateTime

  case class Car(
    id: Option[Int] = Some(0),
    vin: VIN,
    producer: CarProducer,
    model: CarModel,
    color: Color,
    productionYear: ProdYear,
    addedAt: Timestamp = Timestamp.valueOf(LocalDateTime.now()),
    ownerId: Int,
    latitude: Latitude,
    longitude: Longitude
  )

