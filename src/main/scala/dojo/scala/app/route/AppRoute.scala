package dojo.scala.app.route

import akka.http.scaladsl.marshalling._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server._
import cats._
import dojo.scala.app.controller.AppController
import dojo.scala.app.model.AppAction

import scala.language.higherKinds

class AppRoute[M[_]](implicit interpreter: AppAction ~> M,
                     marshaller: ToResponseMarshaller[M[String]]) {
  def route: Route = get {
    parameters("min".as[Int], "max".as[Int]) { (min, max) =>
      complete(AppController.get(min, max).runAction[M])
    }
  }
}
