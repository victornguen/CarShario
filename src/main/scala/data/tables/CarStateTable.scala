package carshario
package data.tables

import data.dbprofiles.H2RefinedProfile.api._
import data.dbprofiles.H2RefinedProfile.mapping._
import data.models.CarState
import data.types.{Latitude, Longitude}

import slick.dbio.Effect
import slick.sql.FixedSqlStreamingAction

import java.time.{LocalDate, LocalDateTime}

class CarStateTable(tag: Tag) extends Table[CarState](tag, "CarStates") {

  def carId = column[Int]("carId")

  def timestamp = column[LocalDateTime]("timestamp")

  def description = column[String]("description")

  def latitude = column[Latitude]("latitude")

  def longitude = column[Longitude]("longitude")

  override def * = (
    carId,
    timestamp,
    description,
    latitude,
    longitude
  ) <> (CarState.tupled, CarState.unapply)

  // compound primary key
  def pk = primaryKey("car_state_pk", (carId, timestamp))

  // foreign keys
  def car = foreignKey("carState_car_fk", carId, CarTable.cars)(_.id)
}

object CarStateTable {
  lazy val carStates      = TableQuery[CarStateTable]
  lazy val insertCarState = carStates returning carStates

  def findInArea(latFrom: Latitude,
                 longFrom: Longitude,
                 latTo: Latitude,
                 longTo: Longitude
  ): Query[CarStateTable, CarState, Seq] = {
    carStates.filter { state =>
      state.latitude.between(latFrom, latTo) && state.longitude.between(longFrom, longTo)
    }
  }
}
