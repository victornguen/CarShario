package carshario
package storage

import data.models.Car

import data.types._

trait CarStorage[F[_]] {

  def createTable: F[Unit]

  def selectAll: F[List[Car]]

  def findById(id: Int): F[Option[Car]]

  def findByVin(vin: VIN): F[Option[Car]]

  def findByProducer(producer: CarProducer): F[List[Car]]

  def findByModel(model: CarModel): F[Option[Car]]

  def findByOwner(ownerId: Int): F[List[Car]]

  def findInArea(latFrom: Latitude, longFrom: Longitude, latTo: Latitude, longTo: Longitude): F[List[Car]]

  def filter(f: Car => Boolean): F[List[Car]]

  def add(car: Car): F[Int]

  def delete(car: Car): F[Int]

}
