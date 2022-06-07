package carshario
package data.models

import data.types.{Birthday, HumanName}

case class Owner(
                  id: Option[Int] = Some(0),
                  firstName: HumanName,
                  lastName: HumanName,
                  birthday: Birthday
                )
