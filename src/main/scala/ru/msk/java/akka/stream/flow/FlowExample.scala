package ru.msk.java.akka.stream.flow

import java.nio.file.Paths

import akka.actor.ActorSystem
import akka.stream.{ActorMaterializer, IOResult}
import akka.stream.scaladsl.{FileIO, Flow, Keep, Sink, Source}
import akka.util.ByteString

import scala.concurrent.Future

object FlowExample {
  implicit val actorSystem = ActorSystem("test")
  implicit val materializer = ActorMaterializer()

  def main(args: Array[String]): Unit = {

    val factorials = Source(1 to 200)

    def lineSink(filename: String): Sink[String, Future[IOResult]] =
      Flow[String].map(s =>
        ByteString(s + "\n")
      )
        .toMat(FileIO.toPath(Paths.get(filename)))(Keep.right)

    factorials
      .map(_.toString)
      .runWith(lineSink("factorial2.txt"))
  }
}
