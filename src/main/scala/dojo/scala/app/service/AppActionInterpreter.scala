package dojo.scala.app.service

import cats.{Id, ~>}
import dojo.scala.app.model.{GetRandomIntAction, AppAction}

import scala.concurrent.{ExecutionContext, Future}
import scala.util.Random

class AppActionInterpreter(implicit ec: ExecutionContext)
  extends (AppAction ~> Future) {

  override def apply[A](action: AppAction[A]): Future[A] = action match {
    case GetRandomIntAction(min, max, onResult) =>
      Future(onResult(min + Random.nextInt(max - min)))
  }
}
