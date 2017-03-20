package WordCount

import akka.actor.Actor

class LineActor extends Actor{

  override def receive = {
    case line: String =>
      sender() forward line.split(" ").length
    case _ => sender() ! 0
  }

}
