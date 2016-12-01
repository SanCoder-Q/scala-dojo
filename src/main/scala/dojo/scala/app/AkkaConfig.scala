package dojo.scala.app

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer

trait AkkaConfig {
  implicit lazy val system = ActorSystem()
  implicit lazy val executionContext = system.dispatcher
  implicit lazy val materializer = ActorMaterializer()
}
