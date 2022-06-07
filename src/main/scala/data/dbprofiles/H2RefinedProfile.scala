package carshario
package data.dbprofiles

import be.venneborg.refined.{RefinedMapping, RefinedSupport}

trait H2RefinedProfile extends slick.jdbc.H2Profile with RefinedMapping with RefinedSupport {
  override val api = new API with RefinedImplicits
}

object H2RefinedProfile extends H2RefinedProfile