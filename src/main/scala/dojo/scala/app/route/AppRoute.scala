package dojo.scala.app.route

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server._
import dojo.scala.app.controller.AppController

object AppRoute {
  val route: Route = get {
    parameters("min".as[Int], "max".as[Int]) { (min, max) =>
      complete(AppController.get(min, max))
    }
  }
}
