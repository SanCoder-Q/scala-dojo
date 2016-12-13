package dojo.scala.app.service
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpRequest
import akka.stream.Materializer
import akka.util.ByteString

import cats.{Id, ~>}
import dojo.scala.app.model.{GetRandomIntAction, AppAction}

import scala.concurrent.{ExecutionContext, Future}
import scala.util.Random

class AppActionInterpreter(implicit ec: ExecutionContext,
                            as: ActorSystem,
                            mz: Materializer) extends (AppAction ~> Future) {

  override def apply[A](action: AppAction[A]): Future[A] = action match {
    case GetRandomIntAction(min, max, onResult) => {
      val host = "https://www.random.org/integers/"
      val params = s"num=1&min=$min&max=$max&col=1&base=10&format=plain&rnd=new"
      for {
        resp <- Http().singleRequest(HttpRequest(uri = s"$host?$params"))
        byte <- resp.entity.dataBytes.runFold(ByteString(""))(_ ++ _)
        body = byte.utf8String
        integer  = body.trim.toInt
      } yield onResult(integer)
    }
  }
}
