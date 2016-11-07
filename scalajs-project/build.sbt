enablePlugins(ScalaJSPlugin)


name := "scalajs-project"
version := "1.0"
scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
    "org.scala-js" %%% "scalajs-dom" % "0.9.1",
    "com.lihaoyi" %%% "scalatags" % "0.6.1"
)

// workbench stuff

workbenchSettings
bootSnippet := "ru.mit.spbau.scalajs.HelloWorld().main();"
updateBrowsers <<= updateBrowsers.triggeredBy(fastOptJS in Compile)
