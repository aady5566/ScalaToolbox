/*{
  //view 'Rational' as a data type
  class Rational(x:Int,y:Int){
  def numer = x
  def denom = y
}
  val object1 = new Rational(1,2)


  def addRational(r:Rational,s:Rational):Rational={
    new Rational(r.numer*s.denom+r.denom*s.numer,r.denom*s.denom)
  }

  def makeString(r:Rational) = r.numer + "/" +r.denom

  makeString(addRational(new Rational(1,2), new Rational(2,3)))
}*/

{
  //view 'Rational' as a data type
  class Rational(x:Int,y:Int){
    //'private': we can only access them from inside the Rational class
    private def gcd(a:Int,b:Int):Int = if(b==0) a else gcd(b,a%b)

    val numer = x/gcd(x,y)

    val denom = y/gcd(x,y)

    def add(that: Rational)={
      new Rational(numer*that.denom+denom*that.numer,denom*that.denom)
    }

    def neg = new Rational(-numer,denom)

    def sub(that:Rational) = add(that.neg)

    override def toString = numer + "/" + denom
  }
  /*object rationals{ //not sure about the function of 'object'
    val object1 = new Rational(1,3)
    val object2 = new Rational(5,7)
    val object3 = new Rational(3,2)
    object1.add(object2)
    object1.neg
    val result = object1.sub(object2).sub(object3)
  }*/
  val x = new Rational(1,2)
  val y = new Rational(2,3)
  x.add(x)
  val object1 = new Rational(1,3)
  val object2 = new Rational(5,7)
  val object3 = new Rational(3,2)
  object1.sub(object2).sub(object3)
}

