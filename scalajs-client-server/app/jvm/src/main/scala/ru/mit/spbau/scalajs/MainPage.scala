package ru.mit.spbau.scalajs

import scalatags.Text.all._

object MainPage {
    val boot = "ru.mit.spbau.scalajs.PointsApplication().main()"
    val skeleton =
        html(
            head(
                script(src:="/app-fastopt.js"),
                link(
                    rel:="stylesheet",
                    href:="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
                ),
                link(
                    rel:="stylesheet",
                    href:="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
                )
            ),
            body(
                div(
                    `class`:="container", // `class` == attr("class")
                    div(
                        `class`:="row",
                        div(
                            `class`:="col-sm-3",
                             h1("Plot")
                        ),
                        div(
                            `class`:="col-sm-3",
                            img(
                                id:="pin",
                                width:=20,
                                height:=20,
                                src:="http://www.myiconfinder.com/uploads/iconsets/256-256-89fdce5084dbe77556cf99f7b22ae7e8-pin.png"
                            )
                        )
                    ),

                    canvas(id:="points-canvas"),

                    div(
                        `class`:="row",
                        div(
                            `class`:="col-sm-6",
                            input(
                                id:="description-in",
                                `type`:="text",
                                `name`:="Description"
                            )
                        ),
                        div(
                            `class`:="col-sm-3",
                            input(
                                id:="submit-points-btn",
                                `type`:="submit",
                                `value`:="Set points"
                            )
                        ),
                        div(
                            `class`:="col-sm-3",
                            input(
                                id:="get-points-btn",
                                `type`:="submit",
                                `value`:="Get points"
                            )
                        )
                    )

                ),
                onload:=boot
            )
        )

}
