package ru.msk.java.akka.collections

object FlatMapExample {

  def main(args: Array[String]): Unit = {

    val fruits = Seq("apple", "banana", "orange")

    println(fruits.map(_.toUpperCase()))
    println(fruits.flatMap(_.toUpperCase()))

    //////
    def toInt(s: String): Option[Int] = {
      try {
        Some(Integer.parseInt(s.trim))
      } catch {
        case e: Exception => None
      }
    }

    val strings = Seq("1", "2", "3", "foo")

    println(strings.map(toInt))
    println(strings.flatMap(toInt))
    println(strings.flatMap(toInt).sum)

  }

}
