package dojo.scala.app.service
import cats.~>
import cats.implicits._
import dojo.scala.app.MyReader
import dojo.scala.app.api.RandomClient
import dojo.scala.app.model.{AppAction, GetRandomIntAction}

import scala.concurrent.ExecutionContext

class AppActionInterpreter(rClient: RandomClient)(implicit ec: ExecutionContext) extends (AppAction ~> MyReader) {

  override def apply[A](action: AppAction[A]): MyReader[A] = action match {
    case GetRandomIntAction(min, max, onResult) =>
      rClient.generateRandomNum(min, max) map onResult
  }
}
