package ru.msk.java.basic

import java.nio.file.Paths

import akka.NotUsed
import akka.actor.ActorSystem
import akka.stream.scaladsl.{FileIO, Source}
import akka.stream.{ActorMaterializer, IOResult}
import akka.util.ByteString

import scala.concurrent.Future

object SimpleSourceExample {

  def main(args: Array[String]): Unit = {
    implicit val system = ActorSystem("QuickStart")
    implicit val materializer = ActorMaterializer()

    val source: Source[Int, NotUsed] = Source(1 to 100)

    val future = source.runForeach(i => println(i))

    val factorials = source.scan(BigInt(1))((acc, next) => acc * next)
    val result: Future[IOResult] =
      factorials.map(num =>
        ByteString(s"$num\n")
      )
        .runWith(FileIO.toPath(Paths.get("factorials.txt")))
  }

}
