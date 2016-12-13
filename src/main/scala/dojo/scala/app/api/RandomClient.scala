package dojo.scala.app.api

import akka.stream.Materializer
import cats.data.ReaderT
import cats.data.Kleisli._
import cats.instances.all._
import dojo.scala.app.MyReader
import dojo.scala.app.model.AppConfig

import scala.concurrent.{ExecutionContext, Future}

import akka.http.scaladsl.model.{HttpResponse, HttpRequest}
import akka.util.ByteString

class RandomClient(httpClient: HttpRequest => Future[HttpResponse])
                  (implicit ec: ExecutionContext, mz: Materializer){
  private def apiHost: MyReader[String] = ask[Future, AppConfig].map(_.apiHost)

  def generateRandomNum(min: Int, max: Int): MyReader[Int] = for {
    h <- apiHost
    params = s"num=1&min=$min&max=$max&col=1&base=10&format=plain&rnd=new"
    body <- getBodyFromUri(uri = s"$h/integers/?$params")
  } yield body.trim.toInt

  private def getBodyFromUri(uri: String): MyReader[String] =
    for {
      resp <- lift(httpClient(HttpRequest(uri = uri)))
      byte <- lift(resp.entity.dataBytes.runFold(ByteString(""))(_ ++ _))
      body = byte.utf8String
    } yield body
}
