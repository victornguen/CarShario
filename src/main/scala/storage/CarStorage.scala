package carshario
package storage

import data.models.Car

import data.types._

trait CarStorage[F[_]] {

  def createTable: F[Unit]

  def createTableWithRewrite: F[Unit]

  def selectAll: F[List[Car]]

  def find(car: Car): F[Option[Car]]

  def findById(id: Int): F[Option[Car]]

  def findByVin(vin: VIN): F[Option[Car]]

  def findByManufacturer(producer: CarManufacturer): F[List[Car]]

  def findByModel(model: CarModel): F[List[Car]]

  def findByOwner(ownerId: Int): F[List[Car]]

  def findInArea(latFrom: Latitude, longFrom: Longitude, latTo: Latitude, longTo: Longitude): F[List[Car]]

  def filter(f: Car => Boolean): F[List[Car]]

  def add(car: Car): F[Either[Throwable, Int]]

  def delete(car: Car): F[Int]

}
