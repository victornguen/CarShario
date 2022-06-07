package carshario
package data.models

import data.types.{Age, HumanName}

case class Owner(
                  id: Option[Int] = Some(0),
                  firstName: HumanName,
                  lastName: HumanName,
                  age: Age
                )
