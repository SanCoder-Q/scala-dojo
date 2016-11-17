package dojo.scala.app

import org.scalacheck.{Prop, Gen}
import org.specs2.ScalaCheck
import org.specs2.mutable.Specification

object MainAppSpec extends Specification with ScalaCheck {
  "test" should {
    "be testable" in {
      Prop.forAll(Gen.posNum[Int]) { (i: Int) =>
        println(s"--- $i ---")
        i must beGreaterThan(0)
      }
    }
  }
}
