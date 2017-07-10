addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.6.0")
addSbtPlugin("net.virtual-void" % "sbt-dependency-graph" % "0.8.2")
addSbtPlugin("com.typesafe.sbt" % "sbt-git" % "0.9.2")
addSbtPlugin("com.eed3si9n" % "sbt-buildinfo" % "0.7.0")

addSbtPlugin("com.artima.supersafe" % "sbtplugin" % "1.1.2")

addSbtPlugin("io.get-coursier" % "sbt-coursier" % "1.0.0-RC3")

resolvers ++= Seq(
   "Artima Maven Repository" at "http://repo.artima.com/releases",
    Resolver.sonatypeRepo("releases"),
    Resolver.sonatypeRepo("snapshots")
)

resolvers += "JBoss" at "https://repository.jboss.org"
