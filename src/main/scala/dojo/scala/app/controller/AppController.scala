package dojo.scala.app.controller

import dojo.scala.app.model.AppAction
import dojo.scala.app.service.AppService

object AppController {
  def get(min: Int, max: Int): AppAction[String] =
    AppService.getRandomInt(min, max).map(i => i.toString)
}
