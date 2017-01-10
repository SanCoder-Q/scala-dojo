package dojo.scala.app.controller

import dojo.scala.app.model.AppAction
import dojo.scala.app.service.AppService

object AppController {
  def get(min: Int, max: Int): AppAction[(Int, String)] =
    AppService.getRandomInt(min, max).map {
      case Some(i) => (200, i.toString)
      case None => (403, "error")
    }
}
