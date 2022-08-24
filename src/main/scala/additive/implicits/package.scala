package carshario
package additive

import cats.effect.IO

import scala.concurrent.Future
import scala.language.implicitConversions

package object implicits {
  implicit def futureToIO[A](f: => Future[A]): IO[A] = IO.fromFuture(IO(f))
}
