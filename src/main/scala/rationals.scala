/**
 * Created by YD on 16/4/29.
 */
package r
object rationals {
  def main(args: Array[String]) {
    /*val x = new Rational(1,3)
    val y = new Rational(5,7)
    val z = new Rational(3,2)
    x.add(x)*/

    val strange = new Rational(1,0)
    println(strange.add(strange))
  }
}


class Rational(x:Int,y:Int){
  //exception here
  require(y !=0 ,"denominator must be nonzero!") //require is used to enforce a 'precondition' on the caller of a function
  //assert(y!=0) //assert is used as to check the code of the function itself
  private def gcd(a:Int,b:Int):Int = if(b==0) a else gcd(b,a%b)
  private val g = gcd(x,y)
  val numer = x/g

  val denom = y/g

  def add(that: Rational)={
    new Rational(numer*that.denom+denom*that.numer,denom*that.denom)
  }

  def neg = new Rational(-numer,denom)

  def sub(that:Rational) = add(that.neg)

  def less(that:Rational) = this.numer*denom < this.denom*that.numer

  def max(that:Rational) = if(this.less(that)) that else this

  override def toString = numer + "/" + denom
}