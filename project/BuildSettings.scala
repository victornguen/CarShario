// SBT
import sbt._
import Keys._

// sbt-assembly
import sbtassembly._
import sbtassembly.AssemblyKeys._

/**
 * To enable any of these you need to explicitly add Settings value to build.sbt
 */
object BuildSettings {

  lazy val buildSettings = Seq(
    addCompilerPlugin(
      "org.typelevel" %% "kind-projector" % "0.13.2" cross CrossVersion.full
    ),
    addCompilerPlugin("com.olegpy" %% "better-monadic-for" % "0.3.1")
  )

  // sbt-assembly settings
  lazy val assemblySettings = Seq(
    assembly / assemblyJarName := {
      moduleName.value + "-" + version.value + ".jar"
    }
  )

//  lazy val helpersSettings = Seq(
//    initialCommands := "import carshario._"
//  )
}
