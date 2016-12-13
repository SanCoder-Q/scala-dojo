package dojo.scala.app.api

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpResponse, HttpRequest}
import akka.stream.Materializer

import scala.concurrent.{ExecutionContext, Future}

class HttpClient(implicit ec: ExecutionContext,
                 as: ActorSystem,
                 mz: Materializer) extends (HttpRequest => Future[HttpResponse]) {
  def apply(req: HttpRequest): Future[HttpResponse] = Http().singleRequest(req)
}
