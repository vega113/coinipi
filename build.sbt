name := "coinipi"

version := "1.0"

scalaVersion := "2.11.8"


mainClass in (Compile, run) := Some("org.wavylabs.Boot")

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.0" % "test"

libraryDependencies += "com.typesafe.akka" %% "akka-stream" % "2.4.9"
libraryDependencies += "com.typesafe.akka" %% "akka-stream-testkit" % "2.4.9"
libraryDependencies += "com.typesafe.akka" %% "akka-testkit" % "2.4.9"
libraryDependencies += "com.typesafe.akka" %% "akka-http-spray-json-experimental" % "2.4.9"
libraryDependencies += "com.typesafe" % "config" % "1.3.0"