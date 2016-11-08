package ru.mit.spbau.scalajs

import scala.scalajs.js.JSApp
import org.scalajs.dom
import org.scalajs.dom.html
import dom.document

object PointsApp extends JSApp {
    override def main(): Unit = {

        val canvas = dom.document.getElementById("points-canvas")
            .asInstanceOf[html.Canvas]

        val renderer = canvas.getContext("2d")
            .asInstanceOf[dom.CanvasRenderingContext2D]

        canvas.width = canvas.parentElement.clientWidth
        canvas.height = canvas.parentElement.clientHeight

        renderer.fillStyle = "#f7f7f7"
        renderer.fillRect(0, 0, canvas.width, canvas.height)

        renderer.fillStyle = "red"
        canvas.onmousedown = {
            (e: dom.MouseEvent) =>
                val rect = canvas.getBoundingClientRect()
                val point = (e.clientX - rect.left, e.clientY - rect.top)
                dom.window.alert(s"You've just added point: ${point}")
                renderer.drawImage(dom.document.getElementById("pin").asInstanceOf[html.Image],
                    point._1, point._2, width = 30, height = 30)
        }
    }
}
