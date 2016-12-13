package dojo.scala.app.api

import akka.stream.Materializer

import scala.concurrent.{ExecutionContext, Future}

import akka.http.scaladsl.model.{HttpResponse, HttpRequest}
import akka.util.ByteString

class RandomClient(httpClient: HttpRequest => Future[HttpResponse])
                  (implicit ec: ExecutionContext, mz: Materializer){
  private val apiHost: String = "https://www.random.org/"

  def generateRandomNum(min: Int, max: Int): Future[Int] = {
    val params = s"num=1&min=$min&max=$max&col=1&base=10&format=plain&rnd=new"
    getBodyFromUri(uri = s"$apiHost/integers/?$params").map(_.trim.toInt)
  }

  private def getBodyFromUri(uri: String): Future[String] =
    for {
      resp <- httpClient(HttpRequest(uri = uri))
      byte <- resp.entity.dataBytes.runFold(ByteString(""))(_ ++ _)
      body = byte.utf8String
    } yield body
}
