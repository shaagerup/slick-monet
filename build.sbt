lazy val root = (project in file("."))
  .settings(
    organization := "com.github.shaagerup",
    name := "slick-monet",
    version := "0.2-SNAPSHOT",
    scalaVersion := "2.12.3",
    crossScalaVersions := Seq("2.12.3"),
    credentials += Credentials("Artifactory Realm", "artifactory.cerno.dk", sys.env.get("ARTIFACTORY_USERNAME").mkString, sys.env.get("ARTIFACTORY_PASSWORD").mkString),
    resolvers +=
      "Artifactory" at "https://artifactory.cerno.dk/artifactory/cerno/",
    publishTo := Some("Artifactory Realm" at "https://artifactory.cerno.dk/artifactory/cerno;build.timestamp=" + new java.util.Date().getTime)
  )

libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick" % "3.2.1",
  "monetdb" % "monetdb-jdbc" % "2.18-SNAPSHOT"
)
