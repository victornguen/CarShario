package carshario
package dao.models

import dao.types.{Birthday, HumanName}

case class Owner(
  id: Option[Int] = Some(0),
  firstName: HumanName,
  lastName: HumanName,
  birthday: Birthday
)
