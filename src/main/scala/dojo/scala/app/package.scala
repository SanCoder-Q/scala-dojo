package dojo.scala

import cats.data.ReaderT
import dojo.scala.app.model.AppConfig

import scala.concurrent.Future

package object app {
  type MyReader[A] = ReaderT[Future, AppConfig, A]
}
