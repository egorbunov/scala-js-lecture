package ru.mit.spbau.scalajs

import akka.actor.ActorSystem
import spray.http.{HttpEntity, MediaTypes}
import spray.routing.SimpleRoutingApp
import scala.util.Properties


object Server extends SimpleRoutingApp {
    var pointsData = PointsData("", List.empty)

    def main(args: Array[String]): Unit = {
        implicit val system = ActorSystem()
        val port = Properties.envOrElse("PORT", "8080").toInt
        startServer("0.0.0.0", port = port){
            get{
                pathSingleSlash{
                    complete{
                        HttpEntity(
                            MediaTypes.`text/html`,
                            MainPage.skeleton.render
                        )
                    }
                } ~
                    getFromResourceDirectory("")
            } ~
            post{
                path("ajax" / "getPoints"){
                    extract(_.request.entity.asString) { e =>
                        complete {
                            upickle.default.write(pointsData)
                        }
                    }
                }
            } ~
            post{
                path("ajax" / "setPoints"){
                    extract(_.request.entity.asString) { e =>
                        complete {
                            pointsData = upickle.default.read[PointsData](e)
                            ""
                        }
                    }
                }
            }
        }
    }
}
