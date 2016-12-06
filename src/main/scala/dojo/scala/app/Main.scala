package dojo.scala.app

import akka.http.scaladsl.marshalling.ToResponseMarshallable
import akka.http.scaladsl.model.{HttpRequest, HttpResponse}
import akka.http.scaladsl.server.Route
import dojo.scala.app.route.AppRoute

import scala.concurrent.Future
import scala.io.StdIn

object Main extends App with Servable with ServerConfig {
  val server = start()
  println(s"Server online at http://$interface:$port/")
  println("Press RETURN to stop...")
  StdIn.readLine()

  server.stop()

  def handler: (HttpRequest) => Future[HttpResponse] = Route.asyncHandler(AppRoute.route)
}
