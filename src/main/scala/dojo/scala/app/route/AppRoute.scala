package dojo.scala.app.route

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server._
import cats._
import dojo.scala.app.AkkaConfig
import dojo.scala.app.controller.AppController
import dojo.scala.app.model.AppAction

import scala.concurrent.Future

class AppRoute(implicit interpreter: AppAction ~> Future) {
  def route: Route = get {
    parameters("min".as[Int], "max".as[Int]) { (min, max) =>
      complete(AppController.get(min, max).runAction[Future])
    }
  }
}
