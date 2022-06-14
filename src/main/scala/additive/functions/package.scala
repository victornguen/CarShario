package carshario
package additive

import cats.effect.IO

import scala.concurrent.Future

package object functions {

  def IOFromFuture[A](f: => Future[A]) = IO.fromFuture(IO(f))
}
