name := "ScalatronBot"

version := "1.0.0"

scalaVersion := "2.9.1"

scalacOptions ++= Seq("-deprecation")

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "1.9.1" % "test",
  "junit" % "junit" % "4.10" % "test",
  "org.mockito" % "mockito-all" % "1.9.0" % "test")
