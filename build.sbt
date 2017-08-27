lazy val root = (project in file("."))
  .settings(
    organization := "com.github.shaagerup",
    name := "slick-monet",
    version := "0.2-SNAPSHOT",
    scalaVersion := "2.12.2",
    crossScalaVersions := Seq("2.12.2")
  )

libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick" % "3.2.1",
  "monetdb" % "monetdb-jdbc" % "2.18-SNAPSHOT"
)
