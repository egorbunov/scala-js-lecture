name := "scalajs-client-server"
scalaVersion in ThisBuild := "2.11.8"
version := "1.0"

lazy val root = project.in(file(".")).
    aggregate(appJS, appJVM).
    settings(
        publish := {},
        publishLocal := {}
    )

val app = crossProject.in(file("app")).
    settings(
        libraryDependencies ++= Seq(
            "com.lihaoyi" %%% "scalatags" % "0.6.1",
            "com.lihaoyi" %%% "upickle" % "0.4.3"
        ),
        scalaVersion := "2.11.8"
    ).
    jsSettings(
        libraryDependencies ++= Seq(
            "org.scala-js" %%% "scalajs-dom" % "0.9.1"
        ),
        jsDependencies +=
            "org.webjars" % "jquery" % "2.1.3" / "2.1.3/jquery.js"
    ).
    jvmSettings(
        libraryDependencies ++= Seq(
            "io.spray" %% "spray-can" % "1.3.2",
            "io.spray" %% "spray-routing" % "1.3.2",
            "com.typesafe.akka" %% "akka-actor" % "2.4.12"
        )
    )

lazy val appJS = app.js
lazy val appJVM = app.jvm.settings(
    (resources in Compile) += (fastOptJS in (appJS, Compile)).value.data
)
