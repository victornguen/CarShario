package carshario
package dao.tables

import dao.types._
import dao.models.User
import dao.dbprofiles.H2RefinedProfile.api._
import dao.dbprofiles.H2RefinedProfile.mapping._

import java.time.LocalDate

class UserTable(tag: Tag) extends Table[User](tag, "Users"){

  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

  def firstName = column[HumanName]("humanName")

  def lastName = column[HumanName]("lastName")

  def birthday = column[Birthday]("birthday")

  def drivingLicenseId = column[DrivingLicenseId]("drivingLicenseId")

  def drivingStartDate = column[LocalDate]("drivingStartDate")

  override def * = (
    id.?,
    firstName,
    lastName,
    birthday,
    drivingLicenseId,
    drivingStartDate
  )<> (User.tupled, User.unapply)
}

object UserTable{
  lazy val users = TableQuery[UserTable]
  lazy val insertUser = users returning users.map(_.id)
}
