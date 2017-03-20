package WordCount

import java.nio.file.{Files, Paths}

import akka.pattern.ask
import akka.actor.{Actor, Props}
import akka.util.Timeout

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.io.Source

class FileActor extends Actor{

  val props = Props[LineActor]
  var wordCount = 0
  implicit val timeout = Timeout(1000 seconds)

  override def receive = {
    case path: String if ifExists(path) =>
      for (line <- Source.fromFile(path).getLines) {
        val lineRef = context.actorOf(props)
        val countRes = (lineRef ? line).mapTo[Int]
        countRes.foreach(count => wordCount = wordCount + count)
      }
      println(s"Total Words in file $wordCount")
    case _ => println("File Not Found")
  }

  def ifExists(path: String) = {
    Files.exists(Paths.get(path))
  }
}
