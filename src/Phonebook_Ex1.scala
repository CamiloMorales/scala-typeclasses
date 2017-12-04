object Phonebook_Ex1 {

  trait ToJson {
    def toJson : String
  }

  case class Record(name:String, lastName: String) extends ToJson {
    def toJson : String = s"""{"name": "${name}", "last_name":"${lastName}"}"""
  }
}

import Phonebook_Ex1._

object TestPlayground_1 extends App {
  val myRecord = Record("Camilo", "Morales")
  println(myRecord.toJson)
}

object TestPlayground_2 extends App {
  val myRecord1 = Record("Camilo", "Morales")
  val myRecord2 = Record("Pedro", "Perez")

  val myListOfRecords: Seq[Record] = Seq(myRecord1, myRecord2)

  //println(myListOfRecords.json)
  println(listToJson(myListOfRecords))

  def listToJson(records : Seq[ToJson]):String = {
    s"""[
       |${ records.map{ _.toJson }.mkString((",\n"))}
       |]""".stripMargin
  }
}