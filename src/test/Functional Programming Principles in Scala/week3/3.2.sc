import r.Rational

/*Classes and Objects are orgainized in packages*/
//get the object or class from packages
object scratch{
  val x= new Rational(1,2)
  x
}
{
  //automatically imported in any Scala Program.
  //package scala
  //package java.lang
  //singleton object scala.Predef
}
{
  /*in java or scala, a class can only have one superclass.
  if a class has several supertypes, you could use traits.
  A traits is declared  like an abstract class (just instead of abstract class)
  * */
  trait Planar{

    def height: Int

    def width: Int

    def surface = height * width
}
  /*Classes, objects and traits cab inherit from AT MOST ONE CLASS but arbitrary many traits.
  * Yet traits cannot have (value) parameters, only classes can.
  * */
}