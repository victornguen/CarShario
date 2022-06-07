package carshario
package data.tables

import data.models.Car

import data.types._
import eu.timepit.refined.auto._
import data.dbprofiles.H2RefinedProfile.api._
import data.dbprofiles.H2RefinedProfile.mapping._

import java.sql.Timestamp

class CarTable(tag: Tag) extends Table[Car](tag, "Cars") {

  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

  def vin = column[VIN]("vin")

  def producer = column[CarProducer]("producer")

  def model = column[CarModel]("model")

  def color = column[Color]("color")

  def productionYear = column[ProdYear]("productionYear")

  def addedAt = column[Timestamp]("addedAt")
  def ownerId = column[Int]("ownerId")
  //    def location = column[Coords]("coords")
  def latitude = column[Latitude]("latitude")
  //
  def longitude = column[Longitude]("longitude")

  override def * =
    (
      id.?,
      vin,
      producer,
      model,
      color,
      productionYear,
      addedAt,
      ownerId,
      latitude,
      longitude
    ) <> (Car.tupled, Car.unapply)
}
