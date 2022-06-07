ThisBuild / version := "0.1.0-RC1"

ThisBuild / scalaVersion := "2.13.8"

lazy val root = (project in file("."))
  .settings(
    name := "CarShario",
    idePackagePrefix := Some("carshario")
  )
  .settings(BuildSettings.buildSettings)
  .settings(BuildSettings.assemblySettings)
  .settings(libraryDependencies ++= Dependencies.all)
