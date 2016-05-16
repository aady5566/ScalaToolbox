/*Class Hierarchies*/
//abstract class
{
  object intsets{
    val t1 = new NonEmpty(3,new Empty,new Empty)
    val t2 = t1 incl 4
  }

  abstract class IntSet{
    def contains(x:Int): Boolean
    def incl(x: Int): IntSet
    def union(other: IntSet): IntSet
  }

  class Empty extends IntSet{
    def contains(x: Int): Boolean = false

    def incl(x:Int):IntSet = new NonEmpty(x,new Empty,new Empty)

    def union(other: IntSet) = other

    override def toString = "."

  }

  class NonEmpty(elem: Int, left: IntSet, right: IntSet) extends IntSet{

    def contains(x:Int):Boolean =
      if (x < elem) left contains x
      else if (x > elem) right contains x
      else true

    def incl(x:Int):IntSet =
      if(x < elem) new NonEmpty(elem,left incl x, right)
      else if(x > elem) new NonEmpty(elem,left,right incl x)
      else this

    override def toString = "{" + left + elem + right + "}"

    def union(other: IntSet):IntSet =
      //((left union right) union other)
      ((left union right) union other) incl elem

  }

  val t1 = new NonEmpty(3,new Empty,new Empty)
  val t2 = t1 incl 4
  val t3 = t2 incl 1 incl 1
  t2 union t3 incl 10
}

{//overrides
  abstract class Base{
  def foo = 1
  def bar: Int
}
  class Sub extends Base{
    override def foo = 2
    def bar = 1
  }
}


{//singleton object, When a singleton object is named the same as a class, it is called a companion object. A companion object must be defined inside the same source file as the class.
class Main {
  def sayHelloWorld() = println("Hello World")
}
  object Main {
    def sayHi() = println("Hi!");
  }
  val x = new Main
  x.sayHelloWorld()
  Main.sayHi()
}