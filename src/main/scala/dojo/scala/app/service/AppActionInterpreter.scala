package dojo.scala.app.service

import cats.{Id, ~>}
import dojo.scala.app.model.{GetRandomIntAction, AppAction}

import scala.util.Random

object AppActionInterpreter extends (AppAction ~> Id) {
  override def apply[A](action: AppAction[A]): Id[A] = action match {
    case GetRandomIntAction(min, max, onResult) =>
      onResult(min + Random.nextInt(max - min))
  }
}
