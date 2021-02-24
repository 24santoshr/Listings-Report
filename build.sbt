name := "listings-report"

version := "0.1"

scalaVersion := "2.12.4"

val akkaVersion = "2.5.14"

libraryDependencies += "com.typesafe.akka" %% "akka-http" % "10.0.11"

libraryDependencies += "com.typesafe.akka" %% "akka-stream" % akkaVersion

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % Test