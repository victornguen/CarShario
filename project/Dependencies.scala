import sbt._

object Dependencies {

  object V {
    // Scala
    val scala   = "2.13.8"
    val decline = "2.2.0"
    val circe   = "0.14.1"

    val http4sVersion = "1.0.0-M29"

    val catsEffect = "3.3.11"
    val fs2        = "3.2.7"

    //database
    val slick      = "3.3.3"
    val postgres   = "42.3.5"
    val h2database = "2.1.212"
    val logback    = "1.2.11"

    // Scala (test only)
    val specs2     = "4.15.0"
    val scalaCheck = "1.15.1"
    val mUnit      = "0.7.29"

    //strong types support
    val refined       = "0.9.29"
    val slick_refined = "0.6.0"
    val newtype       = "0.4.4"
  }

  // Scala
  val all = List(
    "com.monovore" %% "decline"       % V.decline,
    "io.circe"     %% "circe-core"    % V.circe,
    "io.circe"     %% "circe-parser"  % V.circe,
    "io.circe"     %% "circe-generic" % V.circe,

    //http4s
    "org.http4s" %% "http4s-dsl" % V.http4sVersion,
//    "org.http4s" %% "http4s-blaze-server" % V.http4sVersion,
//    "org.http4s" %% "http4s-blaze-client" % V.http4sVersion,
    "org.http4s" %% "http4s-ember-server" % V.http4sVersion,
    "org.http4s" %% "http4s-ember-client" % V.http4sVersion,

    //cats
    "org.typelevel" %% "cats-effect" % V.catsEffect,
    "co.fs2"        %% "fs2-io"      % V.fs2,

    //database
    "com.typesafe.slick" %% "slick"           % V.slick,
    "com.typesafe.slick" %% "slick-hikaricp"  % V.slick,
    "org.postgresql"      % "postgresql"      % V.postgres,
    "com.h2database"      % "h2"              % V.h2database,
    "ch.qos.logback"      % "logback-classic" % V.logback,

    // strong types
    "eu.timepit"   %% "refined"            % V.refined,
    "eu.timepit"   %% "refined-cats"       % V.refined,
    "eu.timepit"   %% "refined-scalacheck" % V.refined,
    "be.venneborg" %% "slick-refined"      % V.slick_refined,
    "io.estatico"  %% "newtype"            % V.newtype,

    //tests
    "org.specs2"     %% "specs2-core" % V.specs2     % Test,
    "org.scalacheck" %% "scalacheck"  % V.scalaCheck % Test,
    "org.scalameta"  %% "munit"       % V.mUnit      % Test
  )
}
