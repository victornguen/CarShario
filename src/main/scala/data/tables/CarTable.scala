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

  def manufacturer = column[CarManufacturer]("producer")

  def model = column[CarModel]("model")

  def color = column[Color]("color")

  def productionYear = column[ProdYear]("productionYear")

  def addedAt = column[Timestamp]("addedAt")

  def ownerId = column[Int]("ownerId")

  def tariff = column[Double]("tariff")

  override def * =
    (
      id.?,
      vin,
      manufacturer,
      model,
      color,
      productionYear,
      addedAt,
      ownerId,
      tariff
    ) <> (Car.tupled, Car.unapply)

  // foreign keys
  def owner = foreignKey("owner_fk", ownerId, OwnerTable.owners)(_.id)
}

object CarTable {
  lazy val cars      = TableQuery[CarTable]
  lazy val insertCar = cars returning cars.map(_.id)
}
