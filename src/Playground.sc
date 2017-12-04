trait ToJson{
  def toJson:String
}

val x = new ToJson{
  override def toJson: String = ???
}



val (firstName,lastName) = "Camilo" -> "Morales"

tuple._1
tuple._2