package dojo.scala.app.service
import cats.~>
import dojo.scala.app.api.RandomClient
import dojo.scala.app.model.{AppAction, GetRandomIntAction}

import scala.concurrent.{ExecutionContext, Future}

class AppActionInterpreter(rClient: RandomClient)(implicit ec: ExecutionContext) extends (AppAction ~> Future) {

  override def apply[A](action: AppAction[A]): Future[A] = action match {
    case GetRandomIntAction(min, max, onResult) =>
      rClient.generateRandomNum(min, max) map onResult
  }
}
