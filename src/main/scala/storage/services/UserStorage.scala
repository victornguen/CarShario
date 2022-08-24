package carshario
package storage.services

import dao.models.User

trait UserStorage[F[_]] {
  def selectAll: F[List[User]]

  def findById(id: Int): F[Option[User]]

  def filter(f: User => Boolean): F[List[User]]

  def insert(user:User): F[Int]

  def delete(id: Int): F[Int]

  def delete(user: User): F[Int]
}
