{
  //general sum
  def sum(f: Int => Int, a: Int, b: Int):Int={
    if(a > b) 0
    else f(a) + sum(f,a+1,b)
  }
  def sumInts(a: Int,b: Int) ={
    //specific method
    def id(x: Int)= x
    sum(id,a,b)
  }
  def sumCube(a:Int, b:Int) = {
    //specific method
    def cube(x: Int) = x * x * x
    sum(cube,a,b)
  }
  sumInts(1,3)
  sumCube(1,3)
}
{
  //anonymous function and currying
  def sum(f: Int => Int)(a: Int, b: Int): Int = {
    def loop(a: Int, acc: Int): Int = {
      if (a > b) acc
      else loop(a + 1, f(a) + acc)
    }
    loop(a, 0)
  }
  //specifict anonymous method here
  sum(x => x * x)(3,5)
}
