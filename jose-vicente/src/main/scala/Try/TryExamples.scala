package Try

import scala.util.{Failure, Random, Success, Try}

object TryExamples  extends App{

  def unsafeMethod() :String = throw new RuntimeException("This is the Runtime Exception")

  val potentialError = Try(unsafeMethod)
  println(potentialError)

  def unsafeBetterMethod: Try[String] = Failure(new RuntimeException)
  def backupMethod: Try[String] = Success("Valid method")

  val betterFallback = unsafeBetterMethod orElse backupMethod
  println(betterFallback)

  class Connection{
    def getConnection:Try[String] = {
      val random = new Random(System.nanoTime())
      if(random.nextBoolean()) Try("Connection is ON")
      else Try(throw new RuntimeException("Exception getting the connection"))
    }
  }

  object HttpServer{
    def getConnectionFromServer : Try[Connection] = {
     val random = new Random(System.nanoTime())
     if(random.nextBoolean()) Try(new Connection)
     else Try(throw new RuntimeException("Error getting the connection from server"))
    }
  }

  val giveMeTheConnection = for{
    connection <- HttpServer.getConnectionFromServer
    concreteConnection <- connection.getConnection
  }yield concreteConnection

  println("Give Me the Connection:: "+giveMeTheConnection)

}
