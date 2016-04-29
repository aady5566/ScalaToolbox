import math.abs
{
  val tolerance = .0001
  def isClose(x:Double,y:Double):Boolean=
    abs((x-y)/x)/x < tolerance
  def fixedPoint(f:Double => Double)(firstGuess:Double)={
    def loop(guess:Double):Double={
      val next = f(guess)
      if(isClose(guess,next)) next
      else loop(next)
    }
    loop(firstGuess)
  }
  fixedPoint(x=> 1+x/2)(1)
  //def sqrt(x:Double) = fixedPoint(y => (y+x/y)/2)(1)
  
  def averageDamp(f:Double => Double)(x:Double) = (x+f(x))/2
  averageDamp(x=>x*x)(5)

  def sqrt(x:Double) = fixedPoint(averageDamp(y=>x/y))(x)
  sqrt(2)
}