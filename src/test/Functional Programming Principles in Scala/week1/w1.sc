import scala.annotation.tailrec
{
  //Read-Eval-Print-Loop
  //substitution model
  //call-by-value vs. call-by-name
  //in scala, we normally use CBV, more efficient
}
{
  def square(x:Double) = x * x
  square(2+2)
  def sumOfSquare(x:Double,y:Double)= square(x) + square(y)
  sumOfSquare(3,2+2)
  val e =5
  (3==3) && true
}
//{
//  //difference between val and def
//  def loop: Boolean = loop
//  def x = loop // a definition
//  val x = loop // lead to an infinite loop
//}
//{


//  def loop: Boolean = loop
//  def and(x: Boolean, y: => Boolean) =
//  if(x) y else false
//  and(false,loop)
//}
{
  def abs(x: Double) = if (x < 0) -x else x
  def sqrt(x: Double) = {
    def sqrtIter(guess: Double): Double =
      if(isGood(guess)) guess
      else sqrtIter(improve(guess))

    def isGood(guess: Double): Boolean = abs(guess*guess - x)/x < .001

    def improve(guess: Double): Double = (guess+x/guess) / 2

    sqrtIter(1)
  }
  sqrt(2)
  sqrt(1e-6)
  sqrt(1e-60)
}
{
  //tail recursive 避免overstackflow
   def fac(n: Int):Int = {
    @tailrec  def loop(acc: Int, n: Int):Int = {
      if (n == 0) acc
      else loop(acc*n,n-1)
    }
    loop(1,n)
  }
  fac(3)
}
{
  def fib(n: Int): Int = {
    @tailrec  def loop(curr: Int, next: Int, n: Int): Int={
      if(n == 1) 1
      else{
        if(n == 2) curr
        else loop(curr+next,curr,n-1)
      }
    }
    loop(1,1,n)
  }
  fib(15)
}