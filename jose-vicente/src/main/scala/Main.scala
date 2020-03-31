import scala.concurrent.{Await, Future, Promise}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.util.Success

object Main extends App {
  println("Hello, World!")
  private var maxMemory: Long = Runtime.getRuntime.maxMemory()
  println("MaxMemory "+maxMemory)
  case class User(name: String)
  case class Transaction(sender:String, receiver:String, amount:Double, status:String)

  object BankingApp{
    val name = "Save Banking"

    def fetchUser(user:String):Future[User] = Future {
      Thread.sleep(500)
      User(user)
    }
    def createTransaction(user:User,mechantName:String, ammount:Double):Future[Transaction] = Future{
      Thread.sleep(1000)
      Transaction(user.name,mechantName, ammount,"SUCCESS")
    }

    def purchase(userName:String, item:String,merchantName:String,cost:Double): String = {

      val transactionStatusFuture = for{
        user <- fetchUser(userName)
        transaction<- createTransaction(user,merchantName,cost)
      } yield transaction.status

      Await.result(transactionStatusFuture,2.seconds)
    }
  }

  println(BankingApp.purchase("Jose Vicente","PS5", "Amazon",1000000))


  val promise = Promise[Int]()
  val future = promise.future

  //consumer
  future.onComplete({
    case Success(value) => println("consumer:: I've received "+value)
  })

  //producer
  val producer = new Thread(
    ()=>{
      println("Producer:: crunching numbers")
      Thread.sleep(1000)
      promise.success(42)
      println("producer done")

    }
  )

  producer.start()
  Thread.sleep(1000)
}