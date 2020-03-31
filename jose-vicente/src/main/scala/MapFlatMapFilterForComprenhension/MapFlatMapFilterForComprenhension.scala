package MapFlatMapFilterForComprenhension

object MapFlatMapFilterForComprenhension extends App{

  val numbers = List(1,2,3,4)
  val chars = List('a','b','c','d')
  val colors = List ("black","white")

  val toPair = (x:Int) => List(x,x+1)

  //map
  println(numbers.map(toPair))


  //flatmap
  println(numbers.flatMap(toPair))

  val combinations = numbers.flatMap(n => chars.map(c => ""+ c + n))
  val combinations2 = numbers.flatMap(n => chars.flatMap(c => ""+ c + n))
  val combinations3 = numbers.flatMap(n => chars.map(c => n + "-"+ c ))

  println("Combinations "+combinations)
  println("Combinations2 "+combinations2)
  println("Combinations3 "+combinations3)

  //for comprenhension

  val comprenhension = for{
    number <- numbers
    c <- chars
    color <- colors
  } yield (number + " " + c + "-" + color)

  println("comprenhension "+comprenhension)
}
