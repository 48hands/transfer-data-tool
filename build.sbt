name := "analysis-data-transfer"

version := "1.0"

scalaVersion := "2.12.8"

libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.4.3"
libraryDependencies += "org.postgresql" % "postgresql" % "42.2.5"
libraryDependencies += "com.typesafe" % "config" % "1.3.4"
libraryDependencies += "io.spray" %%  "spray-json" % "1.3.5"
