object Phonebook {

  trait ToJson[T]{
    def toJson(x:T):String
  }

  implicit class ToJsonSyntax[T](x:T)(implicit typeclass: ToJson[T]){
    def toJson:String = typeclass.toJson(x)
  }

  case class Record(firstName:String, lastName:String, phone:String)

  implicit val recordToJson: ToJson[Record] = new ToJson[Record] {
    def toJson(x:Record):String = {
      s"""{"first_name": "${x.firstName}", "last_name": "${x.lastName}", "phone": "${x.phone}"}"""
    }
  }

}

import Phonebook._

object Ex1 extends App {
  val record = Record("Camilo", "Morales", "010101010101")
  println(record.toJson)
}
