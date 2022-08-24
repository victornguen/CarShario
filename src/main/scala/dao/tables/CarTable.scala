package carshario
package dao.tables

import dao.models.{Car, Owner}
import dao.types._

import eu.timepit.refined.auto._
import dao.dbprofiles.H2RefinedProfile.api._
import dao.dbprofiles.H2RefinedProfile.mapping._

import slick.dbio.Effect
import slick.sql.{FixedSqlStreamingAction, SqlAction}

import java.sql.Timestamp

class CarTable(tag: Tag) extends Table[Car](tag, "Cars") {

  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

  def vin = column[VIN]("vin", O.Unique)

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

  def findById(id: Int): SqlAction[Option[Car], NoStream, Effect.Read] = cars.filter(_.id === id).result.headOption

  def findByVin(vin: VIN): SqlAction[Option[Car], NoStream, Effect.Read] = cars.filter(_.vin === vin).result.headOption

  def findByManufacturer(manufacturer: CarManufacturer): FixedSqlStreamingAction[Seq[Car], Car, Effect.Read] =
    cars.filter(_.manufacturer === manufacturer).result

  def findByModel(model: CarModel): FixedSqlStreamingAction[Seq[Car], Car, Effect.Read] =
    cars.filter(_.model === model).result

  def findByOwner(ownerId: Int): FixedSqlStreamingAction[Seq[Car], Car, Effect.Read] =
    cars.filter(_.ownerId === ownerId).result
}
