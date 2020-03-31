import scala.concurrent.{Await, Future, Promise}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}
import scala.concurrent.duration._

object FutureExample {

  def main(args: Array[String]): Unit = {
    //testFulFillImmediately

    //testInSequence

    val future = first(fulFillImmediately(10), fulFillImmediately(20))

    future.onComplete(
      {
        case Success(value) => println(s"The value is $value")
        case Failure(fail) => println(s"Failure $fail")
      }
    )

    Await.result(future, 2.seconds)
  }

  private def testInSequence = {
    val future = inSequence(fulFillImmediately(10), fulFillImmediately(20))

    future.onComplete(
      {
        case Success(value) => println(s"The value is $value")
        case Failure(fail) => println(s"Failure $fail")
      }
    )

    Await.result(future, 2.seconds)
  }

  private def testFulFillImmediately = {
    val future: Future[Int] = fulFillImmediately(2)

    future.onComplete(
      {
        case Success(value) => println(s"The value is $value")
        case Failure(fail) => println(s"Failure $fail")
      }
    )

    Await.result(future, 2.seconds)
  }

  def fulFillImmediately[T](value:T):Future[T] = {
    println(s"FulFillImmediately $value")
    Future(value)
  }

  def inSequence[A,B](first:Future[A], second:Future[B]):Future[B] = {
    first.flatMap(_ => second)
  }


  def first[A](fa:Future[A], fb:Future[A]) : Future[A] = {
    val promise = Promise[A]

    readFuture(fa,promise)
    readFuture(fb,promise)

    promise.future
  }


  private def readFuture[A](fa: Future[A], promise: Promise[A]) = {
    fa.onComplete({
      case Success(value) => try {
        promise.success(value)
      } catch {
        case exception => println(s"Exception $exception")
      }
      case Failure(value) => try {
        promise.failure(value)
      } catch {
        case exception: Exception => println(exception)
      }
    })
  }
}
