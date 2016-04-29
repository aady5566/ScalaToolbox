/*data abstraction: ability to choose different implementations of the data without affecting clients' view
  (It's a cornerstone of software engineering)*/

{
  //view 'Rational' as a data type
  class Rational(x:Int,y:Int){

    def this(x:Int) = this(x,1)//second constructor

    private def gcd(a:Int,b:Int):Int = if(b==0) a else gcd(b,a%b)

    private val g = gcd(x,y)

    def numer = x/g

    def denom = y/g

    def add(that: Rational)={
      new Rational(numer*that.denom+denom*that.numer,denom*that.denom)
    }

    def neg = new Rational(-numer,denom)

    def sub(that:Rational) = add(that.neg)

    def less(that:Rational) = this.numer*denom < this.denom*that.numer

    def max(that:Rational) = if(this.less(that)) that else this

    override def toString = numer + "/" + denom
  }

  val x = new Rational(1,3)
  val y = new Rational(5,7)
  val z = new Rational(3,2)
  x.add(x)
  x.max(y)
  new Rational(2,4)

}
