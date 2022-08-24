package carshario
package storage.impl.h2

import storage.services.CarStateStorage

import carshario.dao.models.{Car, CarState}
import carshario.dao.types.VIN
import dao.dbprofiles.H2RefinedProfile.api._
import dao.dbprofiles.H2RefinedProfile.mapping._

import be.venneborg.refined.{RefinedMapping, RefinedSupport}
import carshario.dao.dbprofiles.H2RefinedProfile
import dao.tables.CarStateTable._

import carshario.dao.tables.CarStateTable

import scala.concurrent.{ExecutionContext, Future}

class CarStateStorageImpl(config: String)(implicit
    ec: ExecutionContext
) extends CarStateStorage[Future] {

  val db = Database.forConfig(config)

  override def createTable: Future[Unit] = db.run(carStates.schema.createIfNotExists)

  override def createTableWithRewrite: Future[Unit] =
    db.run(carStates.schema.dropIfExists >> carStates.schema.createIfNotExists)

  override def selectAll: Future[List[CarState]] = db.run(carStates.result).map(_.toList)

  override def findAll(car: Car): Future[List[CarState]] =
    db.run(carStates.filter(_.carId === car.id).result).map(_.toList)

  override def findLatest(car: Car): Future[Option[CarState]] =
    db.run(carStates.filter(_.carId === car.id).result.headOption)

  override def findAllByVin(vin: VIN): Future[List[CarState]] =
    db.run(CarStateTable.findByVin(vin).result.map(_.toList))

  override def findLatestByVin(vin: VIN): Future[Option[CarState]] =
    db.run(CarStateTable.findByVin(vin).result.headOption)

  override def findAllByCarId(id: Int): Future[List[CarState]] =
    db.run(CarStateTable.findByCarId(id).result).map(_.toList)

  override def findLatestByCarId(id: Int): Future[Option[CarState]] =
    db.run(CarStateTable.findByCarId(id).result.headOption)

  override def filter(f: CarState => Boolean): Future[List[CarState]] = db.run(carStates.result.map(_.filter(f).toList))

  override def insert(carState: CarState): Future[CarState] = db.run(insertCarState += carState)

  override def delete(carState: CarState): Future[Int] =
    db.run(carStates.filter(s => s.carId === carState.carId && s.timestamp === carState.timestamp).delete)

  override def deleteAll(car: Car): Future[Int] = db.run(carStates.filter(s => s.carId === car.id).delete)
}
