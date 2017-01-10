package dojo.scala.app.service
import cats.~>
import cats.implicits._
import cats.data.ReaderT
import dojo.scala.app.MyReader
import dojo.scala.app.api.RandomClient
import dojo.scala.app.model.{AppConfig, AppAction, GetRandomIntAction}

import scala.concurrent.{Future, ExecutionContext}

class AppActionInterpreter(rClient: RandomClient)(implicit ec: ExecutionContext) extends (AppAction ~> MyReader) {

  override def apply[A](action: AppAction[A]): MyReader[A] = action match {
    case GetRandomIntAction(min, max, onResult) => for {
      quota <- rClient.getCurrentQuota
      optionNum <- if(quota > 0 ) rClient.generateRandomNum(min, max).map(Some(_)) else ReaderT.pure[Future, AppConfig, Option[Int]](None)
    } yield onResult(optionNum)
    //case GetCurrentQuota(onResult) => rClient.getCurrentQuota map onResult
  }
}
