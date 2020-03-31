package pattern.matching

object PatternMatchingExamples extends App{

  System.out.println("Pattern Matching")

  trait Expression
  case class Number(number:Int) extends Expression
  case class Sum(first:Expression,second:Expression) extends Expression
  case class Prod(first:Expression,second:Expression) extends Expression

  def show(expression1:Expression): String = expression1 match {
    case Number(number) => s"$number"
    case Sum(first,second) => show(first)+" + "+ show(second)
    case Prod(first,second) => {
      def maybeShowParentesis(expression:Expression): String = expression match {
        case Sum(_,_) => "(" + show(expression) + ")"
        case _ => show(expression: Expression)
      }
      maybeShowParentesis(first)+" * "+ maybeShowParentesis(second)
    }
  }

  private val sumTwoNumbers: Sum = Sum(Number(2), Number(3))
  private val sumThreeNumbers: Sum = Sum(Sum(Number(89),Number(56)),Number(60))
  private val prodTwoNumbers: Prod = Prod(Number(78),Sum(Number(6),Number(74)))
  System.out.println(show(sumTwoNumbers))
  System.out.println(show(sumThreeNumbers))
  System.out.println(show(prodTwoNumbers))
}
