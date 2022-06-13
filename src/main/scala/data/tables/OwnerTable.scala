package carshario
package data.tables

import data.types._
import data.models.Owner
import eu.timepit.refined.auto._
import data.dbprofiles.H2RefinedProfile.api._
import data.dbprofiles.H2RefinedProfile.mapping._

class OwnerTable(tag: Tag) extends Table[Owner](tag, "Owner") {

  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

  def firstName = column[HumanName]("humanName")

  def lastName = column[HumanName]("lastName")

  def birthday = column[Birthday]("birthday")

  override def * = (
    id.?,
    firstName,
    lastName,
    birthday
  ) <> (Owner.tupled, Owner.unapply)
}

object OwnerTable {
  lazy val owners      = TableQuery[OwnerTable]
  lazy val insertOwner = owners returning owners.map(_.id)
}
