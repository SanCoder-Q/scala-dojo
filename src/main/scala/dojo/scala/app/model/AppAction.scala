package dojo.scala.app.model

import cats.~>

import scala.language.higherKinds

sealed trait AppAction[A] {
  def map[B](f: A => B): AppAction[B] = this match {
    case GetRandomIntAction(min, max, onResult) =>
      GetRandomIntAction(min, max, onResult andThen f)
  }
}

case class GetRandomIntAction[A](min: Int, max: Int, onResult: Option[Int] => A) extends AppAction[A]

object AppAction {
  implicit class RunableAppAction[A](action: AppAction[A]) {
    def runAction[M[_]](implicit interpreter: AppAction ~> M): M[A] = interpreter(action)
  }
}
