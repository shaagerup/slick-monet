lazy val root = (project in file("."))
  .settings(
    organization := "com.github.shaagerup",
    name := "slick-monet",
    version := "0.2-SNAPSHOT",
    scalaVersion := "2.11.7",
    crossScalaVersions := Seq("2.11.7")
  )

libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick" % "3.1.1",
  "monetdb" % "monetdb-jdbc" % "2.18-SNAPSHOT"
)
