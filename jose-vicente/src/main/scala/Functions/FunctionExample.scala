package Functions

object FunctionExample extends App {

  def functionSumNumbers[A]: (A, A) => A= (a:A, b: A) => a
  def functionSumNumbers2[A,B]: (A,B) => (A,B) =  (a: A,b:B) => (a,b)
  def functionSumNumbers3[A,B](a: A, b: B): (A,B) => (A,B) =  {
    println(a+ " "+b)
    (c,d) =>{
      println(c+ " "+d)
      (a,b)
    }
  }

  def functionSumNumbers4[A,B](f: A => B): (A,B) => (A,B) =  {
    (c,d) =>{
      println(c+ " "+d)
      (c,d)
    }
  }

  def functionSumNumbersInt:(Int,Int) => Int = (a:Int,b:Int) => (b+a)

    val sumGeneric = functionSumNumbers3(2,"3")
  println(sumGeneric(2,"Two"))

  val sum = functionSumNumbersInt(2,3)
  println(sum)

}
