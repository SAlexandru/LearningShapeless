name := "Learning"

version := "0.0.1-SANPSHOT"

scalaVersion := "2.12.2"

scalacOptions ++= Seq("-deprecation",
                        "-feature",
                        "-target:jvm-1.8",
                        "-encoding", "UTF-8",
                        "-unchecked",
                        "-deprecation",
                        "-Xfuture",
                        "-Yno-adapted-args",
                        "-Ywarn-dead-code",
                        "-Ywarn-numeric-widen",
                        "-Ywarn-value-discard",
                        "-Ywarn-unused"
                     )

libraryDependencies ++= Seq(
   "io.circe" %% "circe-core",
   "io.circe" %% "circe-generic",
   "io.circe" %% "circe-parser"
).map(_ % "0.8.0")

libraryDependencies ++= Seq(
  "com.chuusai" %% "shapeless" % "2.3.2",
  "org.typelevel" %% "cats" % "0.9.0",
  "org.scalaz" %% "scalaz-core" % "7.2.13",
  "com.propensive" %% "magnolia" % "0.1.0",
  "org.scalactic" %% "scalactic" % "3.0.1"
)

resolvers ++= Seq(
   "Artima Maven Repository" at "http://repo.artima.com/releases",
    Resolver.sonatypeRepo("releases"),
    Resolver.sonatypeRepo("snapshots")
)


