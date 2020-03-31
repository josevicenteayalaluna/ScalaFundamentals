package Functions

object FunctionMultipleParameters extends App{

  def curriedFormatter(c:String)(d:Double):String = c.format(d)

  val result: String = curriedFormatter("%1.3f")(Math.PI)
  println(result)

  val standardFormat: Double => String = curriedFormatter("%4.2f")

  println(standardFormat(Math.PI))

}
