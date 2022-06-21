package carshario
package storage

import data.models.Car
import data.types.{CarManufacturer, CarModel, Latitude, Longitude, VIN}
import data.dbprofiles.H2RefinedProfile.api._
import data.dbprofiles.H2RefinedProfile.mapping._
import data.tables._
import data.tables.CarTable._
import additive.functions._
import exceptions._

import cats.effect.IO
import cats.implicits._
import cats.syntax._

import scala.concurrent.{ExecutionContext, Future}

class CarStorageImpl(config: String)(implicit ec: ExecutionContext) extends CarStorage[IO] {

  lazy val db = Database.forConfig(config)

  override def createTable: IO[Unit] = IOFromFuture {
    db.run(cars.schema.createIfNotExists)
  }

  override def createTableWithRewrite: IO[Unit] = IOFromFuture {
    db.run(cars.schema.dropIfExists >> cars.schema.createIfNotExists)
  }

  override def selectAll: IO[List[Car]] = IOFromFuture {
    db.run(cars.result).map(_.toList)
  }

  override def findById(id: Int): IO[Option[Car]] = IOFromFuture {
    db.run(CarTable.findById(id))
  }

  override def findByVin(vin: VIN): IO[Option[Car]] = IOFromFuture {
    db.run(CarTable.findByVin(vin))
  }

  override def findByManufacturer(manufacturer: CarManufacturer): IO[List[Car]] = IOFromFuture {
    db.run(CarTable.findByManufacturer(manufacturer).map(_.toList))
  }

  override def findByModel(model: CarModel): IO[List[Car]] = IOFromFuture {
    db.run(CarTable.findByModel(model).map(_.toList))
  }

  override def findByOwner(ownerId: Int): IO[List[Car]] = IOFromFuture {
    db.run(CarTable.findByOwner(ownerId).map(_.toList))
  }

  override def find(car: Car): IO[Option[Car]] = findByVin(car.vin)

  override def findInArea(latFrom: Latitude, longFrom: Longitude, latTo: Latitude, longTo: Longitude): IO[List[Car]] =
    IOFromFuture {
      db.run {
        val states = CarStateTable.findInArea(latFrom, longFrom, latTo, longTo)
        val cars = for {
          (_, car) <- states join CarTable.cars on (_.carId == _.id)
        } yield car
        cars.result.map(_.toList)
      }
    }

  override def filter(f: Car => Boolean): IO[List[Car]] = IOFromFuture {
    db.run(
      cars.result
        .map(_.filter(f))
        .map(_.toList)
    )
  }

  override def add(car: Car): IO[Either[Throwable, Int]] = {
    val mayBeCar = db.run(CarTable.findByVin(car.vin))
    val res = mayBeCar.flatMap {
      case Some(_) => Future.successful(Left(new ObjectAlreadyExists(car)))
      case None        => db.run(insertCar += car).map(Right(_))
    }
    IOFromFuture(res)
  }

  override def delete(car: Car): IO[Int] = IOFromFuture {
    db.run(cars.filter(_.vin === car.vin).delete)
  }
}
