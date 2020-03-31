package Options

import scala.util.Random

object Options extends App{

  def unsafeMethoid: String = null
  def safeMethod:String = "This is a text"

  println(Some(unsafeMethoid))
  println(Some(safeMethod))

  val optionSafeMethod: Option[String] = Some(safeMethod)
  private val maybeString: Option[String] = optionSafeMethod.flatMap(o => Some(o)).map(l => l)
  private val maybeString1: Option[String] = maybeString.map(s => s.toString)
  println(maybeString1)


  val config: Map[String,String] = Map(
    "host" -> "10.10.120.0",
    "port" -> "8080"
  )

  class Connection{
    def connect = "Connected"
  }

  object Connection {
    val random = new Random(System.nanoTime())
    def apply(host:String, port:String):Option[Connection] =
      if(random.nextBoolean()) Some(new Connection)
      else None
  }

  val connected: Option[String] = for{
    host <- config.get("host")
    port <- config.get("port")
    connection <- Connection(host,port)
  } yield connection.connect

  println(connected)


}
