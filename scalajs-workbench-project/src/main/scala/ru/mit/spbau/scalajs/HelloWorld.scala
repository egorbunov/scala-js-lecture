package ru.mit.spbau.scalajs

import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport
import scalatags.Text.all._

@JSExport
object HelloWorld extends JSApp {
    @JSExport
    def main(): Unit = {
        println("Hello, world!")

        val x =
            html(
                head(

                ),
                body(
                    div(
                        h1(id:="title", "Scala.js"),
                        p("Ummy")
                    )
                )
            )

        println(x)
    }
}

