package carshario

package object exceptions {
  sealed abstract class DatabaseException(message: String) extends Exception(message)

  final class ObjectAlreadyExists[A](obj: A)
      extends DatabaseException(s"object $obj is already exists in database")

  final class ObjectIsNotExists[A](obj: A) extends DatabaseException(s"object $obj is not exists")

}
