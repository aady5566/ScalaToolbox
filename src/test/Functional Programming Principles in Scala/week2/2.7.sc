/*change the identifiers*/

{

  class Rational(x:Int,y:Int){

    def this(x:Int) = this(x,1)//second constructor

    private def gcd(a:Int,b:Int):Int = if(b==0) a else gcd(b,a%b)

    private val g = gcd(x,y)

    def numer = x/g

    def denom = y/g

    def + (that: Rational)={
      new Rational(numer*that.denom+denom*that.numer,denom*that.denom)
    }

    def unary_- = new Rational(-numer,denom) //特殊用法 unary prefix: 原本的<x>.something 變成 somthing<x>

    def - (that:Rational) = this + -that //特殊用法

    def < (that:Rational) = this.numer*denom < this.denom*that.numer

    def max(that:Rational) = if(this < that) that else this

    override def toString = numer + "/" + denom
  }

  val x = new Rational(1,2)
  val y = new Rational(5,7)
  val z = new Rational(2,3)
  x + x
  x < y
  z - x

}
