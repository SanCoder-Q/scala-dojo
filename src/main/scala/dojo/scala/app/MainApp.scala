package dojo.scala.app

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpResponse, HttpRequest}
import akka.stream.ActorMaterializer

import scala.concurrent.Future


object MainApp {
  def main(array: Array[String]): Unit = {
    implicit val system = ActorSystem()
    implicit val executionContext = system.dispatcher
    implicit val materializer = ActorMaterializer()

    val handler: HttpRequest => Future[HttpResponse] = ???

    Http().bindAndHandleAsync(handler, "localhost", 8080)
  }
}
