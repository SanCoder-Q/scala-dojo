name := "scala-dojo"

scalaVersion := "2.11.8"
sbtVersion := "0.13.12"

val specs2Version = "3.8.6"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http-core" % "2.4.11",
  "com.typesafe.akka" %% "akka-http-experimental" % "2.4.11",
  "de.heikoseeberger" %% "akka-http-circe" % "1.10.1",
  "com.typesafe.akka" %% "akka-http-testkit" % "2.4.11",
  "io.circe" %% "circe-core" % "0.5.1",
  "io.circe" %% "circe-generic" % "0.5.1",
  "io.circe" %% "circe-parser" % "0.5.1",
  "org.atnos" %% "eff-cats" % "2.0.0-RC17",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.5.0",
  "ch.qos.logback" % "logback-classic" % "1.1.7",
  "org.specs2" %% "specs2-core" % specs2Version % "test",
  "org.specs2" %% "specs2-cats" % specs2Version % "test",
  "org.specs2" %% "specs2-scalacheck" % specs2Version % "test"
)

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

initialCommands in console := "import cats._, data._, implicits._"

addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.2")
addCompilerPlugin("com.milessabin" % "si2712fix-plugin_2.11.8" % "1.2.0")
