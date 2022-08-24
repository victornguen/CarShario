package carshario
package storage.services

import dao.models.{Car, CarState}
import dao.types.VIN

trait CarStateStorage[F[_]] {

  def createTable: F[Unit]

  def createTableWithRewrite: F[Unit]

  def selectAll: F[List[CarState]]

  def findAll(car:Car): F[List[CarState]]

  def findLatest(car:Car): F[Option[CarState]]

  def findAllByVin(vin: VIN): F[List[CarState]]

  def findLatestByVin(vin: VIN): F[Option[CarState]]

  def findAllByCarId(id: Int): F[List[CarState]]

  def findLatestByCarId(id: Int): F[Option[CarState]]

  def filter(f: CarState => Boolean): F[List[CarState]]

  def insert(carState: CarState): F[CarState]

  def delete(carState: CarState): F[Int]

  def deleteAll(car: Car): F[Int]
}
