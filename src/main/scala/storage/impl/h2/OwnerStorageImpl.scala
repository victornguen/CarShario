package carshario
package storage.impl.h2

import dao.dbprofiles.H2RefinedProfile
import dao.dbprofiles.H2RefinedProfile.api._
import dao.models.Owner
import dao.tables.OwnerTable
import dao.tables.OwnerTable.{insertOwner, owners}
import exceptions.ObjectAlreadyExists
import storage.services.OwnerStorage

import scala.concurrent.{ExecutionContext, Future}

class OwnerStorageImpl(config:String)(implicit ec:ExecutionContext) extends OwnerStorage[Future]{
  val db: H2RefinedProfile.backend.Database = Database.forConfig(config)

  override def selectAll: Future[List[Owner]] = db.run(owners.result).map(_.toList)

  override def findById(id: Int): Future[Option[Owner]] = db.run(OwnerTable.findById(id).result.headOption)

  override def filter(f: Owner => Boolean): Future[List[Owner]] = db.run(owners.result.map(_.filter(f).toList))

  override def insert(owner: Owner): Future[Either[Throwable, Int]] = {
    val mayBeOwner = db.run(OwnerTable.findById(owner.id.get).result.headOption)
    mayBeOwner.flatMap {
      case Some(value) => Future.successful(Left(new ObjectAlreadyExists(value)))
      case None => db.run(insertOwner += owner).map(Right(_))
    }
  }

  override def delete(owner: Owner): Future[Int] = db.run(owners.filter(_.id === owner.id).delete)
}
