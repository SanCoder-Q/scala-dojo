package dojo.scala.app.service

import dojo.scala.app.model.{GetRandomIntAction, AppAction}

import scala.util.Random

object AppService {
  def getRandomInt(min: Int, max: Int): AppAction[Option[Int]] =
    GetRandomIntAction(min, max, identity)
}
