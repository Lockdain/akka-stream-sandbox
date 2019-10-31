package ru.msk.java.akka.stream.timebased

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.Source
import scala.concurrent.duration._

object TimeBasedProcessing {
  def main(args: Array[String]): Unit = {
    implicit val system = ActorSystem("Test")
    implicit val materializer = ActorMaterializer()

    val factorials = Source(1 to 100)

    factorials
      .zipWith(Source(1 to 100))((num, idx) => s"$idx! = $num")
      .throttle(1, 1.second)
      .runForeach(println)
  }
}
