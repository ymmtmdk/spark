name := "KMeansIris"

version := "1.0"

scalaVersion := "2.11.4"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "1.2.0",
  "org.apache.spark" %% "spark-mllib"  % "1.2.0"
)
