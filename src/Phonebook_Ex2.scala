object Phonebook_Ex2 {

  trait ToJson[T]{ def toJson(x:T):String }

  implicit class ToJsonSyntax[T](x:T)(implicit typeclass: ToJson[T]){
    def json:String = typeclass.toJson(x)
  }

  case class Record(firstName:String, lastName:String)

  /*implicit val recordToJson: ToJson[Record] = new ToJson[Record] {
    def toJson(x:Record): String = {
      s"""{"first_name": "${x.firstName}", "last_name": "${x.lastName}"}"""
    }
  }*/

/*  implicit def recordToJson[T](implicit typeclass:ToJson[T]):ToJson[List[T]] = new ToJson[T]{
    def toJson(x:T): String = {
      s"""{"first_name": "${x.firstName}", "last_name": "${x.lastName}"}"""
    }
  }*/

    implicit def recordToJson: ToJson[Record] = new ToJson[Record]{
      def toJson(x:Record): String = {
      s"""{"first_name": "${x.firstName}", "last_name": "${x.lastName}"}"""
    }
  }

}

import Phonebook_Ex2._

object Ex1 extends App {
  val record: Record = Record("Camilo", "Morales")
  println(record.json)
}

object Ex2 extends App {
  implicit def listToJson[T](implicit typeclass:ToJson[T]):ToJson[List[T]] = new ToJson[List[T]]{
    def toJson(x:List[T]):String =
      s"""[
         |${ x.map{ e => e.json }.mkString((",\n"))}
         |]""".stripMargin
  }

/*  implicit val listToJson: ToJson[List[Record]] = new ToJson[List[Record]] {
    def toJson(x:List[Record]):String =
      s"""[
         |${ x.map{ e => e.json }.mkString((",\n"))}
         |]""".stripMargin
    }*/

  val recordList = List(
    Record("Camilo", "Morales"),
    Record("Roman", "Abramovich")
  )

  println(recordList.json)

}

/*object E4_RecordsList extends App {
  implicit def listToJson[T](implicit typeclass: ToJson[T]): ToJson[List[T]] = new ToJson[List[T]] {
    def toJson(x: List[T]): String =
      s"""[
         |  ${x.map { e => e.toJson }.mkString(",\n  ")}
         |]""".stripMargin
  }

  val records = List(
    Record("John", "Smith"),
    Record("Tom" , "Johnson")
  )
  println(records.toJson)
}*/
