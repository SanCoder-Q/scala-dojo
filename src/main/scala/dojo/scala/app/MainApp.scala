package dojo.scala.app

import akka.http.scaladsl.model.{HttpRequest, HttpResponse}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route

import scala.concurrent.Future
import scala.io.StdIn

object Main extends App with Servable {
  val server = start()
  println(s"Server online at http://$interface:$port/")
  println("Press RETURN to stop...")
  StdIn.readLine()

  server.stop()

  def handler: (HttpRequest) => Future[HttpResponse] = Route.asyncHandler {
    get {
      complete("hello world")
    }
  }
}
