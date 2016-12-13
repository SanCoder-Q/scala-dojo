package dojo.scala.app

import akka.http.scaladsl.model.{HttpRequest, HttpResponse}
import akka.http.scaladsl.server.Route
import dojo.scala.app.api.{RandomClient, HttpClient}
import dojo.scala.app.model.AppConfig
import dojo.scala.app.route.AppRoute
import dojo.scala.app.service.AppActionInterpreter

import scala.concurrent.Future
import scala.io.StdIn

object Main extends App with AkkaConfig with Servable with ServerConfig {
  val randomClient = new RandomClient(new HttpClient())
  implicit val appInterpreter = new AppActionInterpreter(randomClient)

  private val config: AppConfig = AppConfig(apiHost = "https://www.random.org/")
  val route = new AppRoute[Future](config).route

  val server = start()
  println(s"Server online at http://$interface:$port/")
  println("Press RETURN to stop...")
  StdIn.readLine()

  server.stop()

  def handler: (HttpRequest) => Future[HttpResponse] = {
    Route.asyncHandler(route)
  }
}
