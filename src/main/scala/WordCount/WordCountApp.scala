package WordCount

import akka.actor.{ActorSystem, Props}

object WordCountApp extends App{

  val system = ActorSystem("WordCount")
  val fileProps = Props[FileActor]
  val fileRef = system.actorOf(fileProps)

  fileRef ! "./file.txt"

}
