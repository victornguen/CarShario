package carshario
package data.models

import data.types._

import java.sql.Timestamp
import java.time.LocalDateTime

/** @param id
  *   \- unique identifier in database
  * @param vin
  *   \- VIN(vehicle identification number) of a car, also called chassis number or frame number
  * @param manufacturer
  *   \- name of manufacturer
  * @param model
  *   \- model number of car
  * @param productionYear
  *   \- year of production
  * @param ownerId
  *   \- FK on owner table
  * @param tariff
  *   \- cost of rent per time unit
  */
case class Car(
    id: Option[Int] = Some(0),
    vin: VIN,
    manufacturer: CarManufacturer,
    model: CarModel,
    color: Color,
    productionYear: ProdYear,
    addedAt: Timestamp = Timestamp.valueOf(LocalDateTime.now()),
    ownerId: Int,
    tariff: Double
)
