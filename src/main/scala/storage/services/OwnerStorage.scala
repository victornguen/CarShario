package carshario
package storage.services

import dao.models.Owner

trait OwnerStorage[F[_]] {
  def selectAll: F[List[Owner]]

  def findById(id:Int): F[Option[Owner]]

  def filter(f: Owner => Boolean): F[List[Owner]]

  def insert(owner: Owner): F[Either[Throwable, Int]]

  def delete(owner: Owner): F[Int]
}
