package ru.mit.spbau.scalajs

import org.scalajs.dom
import org.scalajs.dom.ext.Ajax
import org.scalajs.dom.html
import org.scalajs.dom.html.{Canvas, Input}

import scala.collection.mutable
import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue
import scala.scalajs.js.JSApp

object PointsApplication extends JSApp {
    val points = mutable.MutableList[(Double, Double)]()

    override def main(): Unit = {
        val canvas = dom.document.getElementById("points-canvas")
            .asInstanceOf[Canvas]

        setupCanvas(canvas)

        val descriptionField = dom.document.getElementById("description-in")
            .asInstanceOf[html.Input]
        val submitDescriptionBtn = dom.document.getElementById("submit-points-btn")
            .asInstanceOf[html.Input]
        setupSubmitBtn(submitDescriptionBtn, descriptionField)

        val getPointsBtn = dom.document.getElementById("get-points-btn")
            .asInstanceOf[html.Input]
        setupGetPointsBtn(getPointsBtn, descriptionField, canvas)
    }

    def addPoint(point: (Double, Double), canvas: Canvas) = {
        val renderer = canvas.getContext("2d")
            .asInstanceOf[dom.CanvasRenderingContext2D]
        renderer.drawImage(dom.document.getElementById("pin").asInstanceOf[html.Image],
            point._1, point._2, width = 30, height = 30)
        points += point
    }

    def clearCanvas(canvas: Canvas) = {
        val renderer = canvas.getContext("2d")
            .asInstanceOf[dom.CanvasRenderingContext2D]
        renderer.fillStyle = "#f7f7f7"
        renderer.fillRect(0, 0, canvas.width, canvas.height)
    }

    def setupCanvas(canvas: Canvas): Unit = {
        canvas.width = canvas.parentElement.clientWidth
        canvas.height = canvas.parentElement.clientHeight
        clearCanvas(canvas)

        canvas.onmousedown = {
            (e: dom.MouseEvent) =>
                val rect = canvas.getBoundingClientRect()
                val point = (e.clientX - rect.left, e.clientY - rect.top)
                dom.window.alert(s"You've just added point: ${point}")
                addPoint(point, canvas)
        }
    }

    def setupSubmitBtn(submitDescriptionBtn: Input, descField: Input): Unit = {
        submitDescriptionBtn.onclick = {
            (e: dom.MouseEvent) =>
                val description = descField.value
                Ajax.post(
                    "/ajax/setPoints",
                    data = upickle.default.write(PointsData(description, points))
                ).onComplete{ p =>
                    if (p.isFailure) {
                        dom.window.alert("Failed to send points to server!")
                    }
                }
        }
    }

    def setupGetPointsBtn(getPointsBtn: Input, descriptionField: Input, canvas: Canvas): Unit = {
        clearCanvas(canvas)
        getPointsBtn.onclick = {
            (e: dom.MouseEvent) =>
                Ajax.post("/ajax/getPoints").foreach{ xhr =>
                    val data = upickle.default.read[PointsData](xhr.responseText)
                    descriptionField.value = data.description
                    for (point <- data.points) {
                        addPoint(point, canvas)
                    }
                }
        }
    }


}
