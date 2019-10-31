package ru.msk.java.basic

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Flow, Source}

object SimpleStreamExample {

  def main(args: Array[String]): Unit = {
    implicit val system = ActorSystem("Sys")
    implicit val materializer = ActorMaterializer()

    val numbers = 1 to 1000

   /* // Create a Source which iterates over the number sequence
    val numberSource: Source[Int, NotUsed] = Source.fromIterator(() => numbers.iterator)

    // Only let even numbers to pass through the flow
    val isEvenFlow: Flow[Int, Int, NotUsed] = Flow[Int].filter(num => num % 2 == 0)

    // Create an even numbers source
    val evenNumbersSource: Source[Int, NotUsed] = numberSource.via(isEvenFlow)

    // A sink that will write its input to the console
    val consoleSink: Sink[Int, Future[Done]] = Sink.foreach[Int](println)

    // Connect the source with the sink
    evenNumbersSource.runWith(consoleSink)*/

    // Wiring all together
    Source.fromIterator(() =>
      numbers.iterator
    )
      .via(Flow[Int].filter(num =>
        num % 2 == 0
      ))
      .via(Flow[Int]
        .map( _ * 2)
        .limit(15)
      )
      .runForeach(println)
      /*.runWith(Sink.foreach(
        println
      ))*/
  }

}
