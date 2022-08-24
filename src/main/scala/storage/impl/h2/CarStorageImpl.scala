package carshario
package storage.impl.h2

import dao.dbprofiles.H2RefinedProfile.api._
import dao.dbprofiles.H2RefinedProfile.mapping._
import dao.models.Car
import dao.tables.CarTable._
import dao.tables._
import dao.types._
import exceptions._
import storage.services.CarStorage

import be.venneborg.refined.{RefinedMapping, RefinedSupport}

import scala.concurrent.{ExecutionContext, Future}

class CarStorageImpl(config: String)(implicit ec: ExecutionContext) extends CarStorage[Future] {

  lazy val db = Database.forConfig(config)

  override def createTable: Future[Unit] = db.run(cars.schema.createIfNotExists)

  override def createTableWithRewrite: Future[Unit] = db.run(cars.schema.dropIfExists >> cars.schema.createIfNotExists)

  override def selectAll: Future[List[Car]] =
    db.run(cars.result).map(_.toList)

  override def findById(id: Int): Future[Option[Car]] = db.run(CarTable.findById(id))

  override def findByVin(vin: VIN): Future[Option[Car]] = db.run(CarTable.findByVin(vin))

  override def findByManufacturer(manufacturer: CarManufacturer): Future[List[Car]] =
    db.run(CarTable.findByManufacturer(manufacturer).map(_.toList))

  override def findByModel(model: CarModel): Future[List[Car]] = db.run(CarTable.findByModel(model).map(_.toList))

  override def findByOwner(ownerId: Int): Future[List[Car]] = db.run(CarTable.findByOwner(ownerId).map(_.toList))

  override def find(car: Car): Future[Option[Car]] = findByVin(car.vin)

  override def findInArea(latFrom: Latitude, longFrom: Longitude, latTo: Latitude, longTo: Longitude): Future[List[Car]] =
    db.run {
        val states = CarStateTable.findInArea(latFrom, longFrom, latTo, longTo)
        val cars = for {
          (_, car) <- states join CarTable.cars on (_.carId === _.id)
        } yield car
        cars.result
          .map(_.toList)
      }

  override def filter(f: Car => Boolean): Future[List[Car]] = db.run(
      cars.result
        .map(_.filter(f))
        .map(_.toList)
    )

  override def insert(car: Car): Future[Either[Throwable, Int]] = {
    val mayBeCar: Future[Option[Car]] = db.run(CarTable.findByVin(car.vin))
    mayBeCar.flatMap {
      case Some(_) => Future.successful(Left(new ObjectAlreadyExists(car)))
      case None    => db.run(insertCar += car).map(Right(_))
    }
  }

  override def delete(car: Car): Future[Int] = db.run(cars.filter(_.vin === car.vin).delete)
}
