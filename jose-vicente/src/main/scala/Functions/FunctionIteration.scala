package Functions

object FunctionIteration extends App{

  def nTimes(f: Int =>Int, n:Int): (Int =>Int) =
    if(n <= 0) {
      val intToInt1: Int => Int = (x: Int) => x
      intToInt1
    }
    else (x:Int) => {
      val intToInt: Int => Int = nTimes(f, n - 1)
      intToInt(f(x))
    }

  private val intToInt: Int => Int = (x: Int) => x + 1
  val plus10 = nTimes(intToInt,10)
  println(plus10(1))



}
