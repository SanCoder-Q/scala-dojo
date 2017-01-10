package dojo.scala.app.route

import akka.http.scaladsl.marshalling._
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server._
import cats._
import cats.data.ReaderT
import dojo.scala.app.controller.AppController
import dojo.scala.app.model.{AppConfig, AppAction}

import scala.language.higherKinds

class AppRoute[M[_]](config: AppConfig)(implicit interpreter: AppAction ~> ReaderT[M, AppConfig, ?],
                     marshaller: ToResponseMarshaller[M[(Int, String)]]) {

  def route: Route = get {
    parameters("min".as[Int], "max".as[Int]) { (min, max) =>
      complete(AppController
        .get(min, max)
        .runAction[ReaderT[M, AppConfig, ?]]
        .run(config))
    }
  }
}
