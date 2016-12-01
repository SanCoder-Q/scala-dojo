package dojo.scala.app

import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpResponse, HttpRequest}

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

trait Servable extends AkkaConfig with ServerConfig {
  def handler: HttpRequest => Future[HttpResponse]

  def start() = new Server(Await.result(Http().bindAndHandleAsync(handler, interface, port), Duration.Inf))

  class Server(private val binding: Http.ServerBinding) {
    def stop() = binding.unbind.onComplete(_ => system.terminate())
  }
}
