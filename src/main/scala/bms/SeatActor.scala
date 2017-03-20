package bms
import akka.actor.Actor


class SeatActor extends Actor{

  var seatsList = List("A", "B", "C", "D", "E")
  override def receive = {
    case seat: String if seatsList.contains(seat)=>
      seatsList = seatsList diff List(seat)
      sender() ! "Seat Booked Congrats!!!!"
    case _ => sender() ! "Seat not available Sorry for inconvenience"
  }

}
