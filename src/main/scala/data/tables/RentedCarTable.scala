package carshario
package data.tables
import data.dbprofiles.H2RefinedProfile.api._
import data.dbprofiles.H2RefinedProfile.mapping._

import data.models.RentedCar

import java.time.LocalDateTime


class RentedCarTable(tag: Tag) extends Table[RentedCar](tag, "RentedCars"){

  def carId = column[Int]("carId")

  def userId = column[Int]("userId")

  def timeFrom = column[LocalDateTime]("timeFrom")

  def timeTo = column[Option[LocalDateTime]]("timeTo")

  override def * = (
    carId,
    userId,
    timeFrom,
    timeTo
  ) <> (RentedCar.tupled, RentedCar.unapply)

  //compound primary key
  def pk = primaryKey("rented_car_pk", (carId, userId, timeFrom))

  //foreign keys
  def car = foreignKey("car_fk", carId, CarTable.cars)(_.id)
  def user = foreignKey("user_fk", userId, UserTable.users)(_.id)
}

object RentedCarTable{
  lazy val rentedCars = TableQuery[RentedCarTable]
  lazy val insertRentedCar = rentedCars returning rentedCars
}