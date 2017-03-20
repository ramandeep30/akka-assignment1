package bms

import akka.actor.{Actor, ActorRef}

class UserActor(seatRef: ActorRef) extends Actor{

  override def receive = {
    case seat: String =>
      println("Your request is being processed")
      seatRef forward seat
    case _ => sender() ! "Invalid Request"
  }

}
