{
 def sum(f:Int=>Int)(a:Int,b:Int):Int={
   if(a > b)0 else f(a) + sum(f)(a+1,b)
 }
  def cube(x: Int) = x * x * x
  //sum(cube)(1,3)
  def newSum=sum(cube)_ //currying後，參數間可以拆解，不會被綁死，定義上也不需要有完整的參數
}
{
  def product(f:Int=>Int)(a:Int,b:Int):Int={
    if(a > b) 1
    else f(a) * product(f)(a+1,b)
  }
  product(x=>x * x)(3,4)
  def fact(n:Int) = product(x => x)(1,n) //factorial in terms of 'product'
}
{
  //more general function
  def mapReduce(f: Int=>Int, combineWay: (Int,Int)=>Int,start:Int)(a:Int,b:Int):Int={
    if (a > b) start
    else combineWay(f(a),mapReduce(f,combineWay,start)(a+1,b))
  }
  def prod(f:Int=>Int)(a:Int,b:Int):Int= mapReduce(f,(x,y)=>x*y,1)(a,b)
  def sum(f:Int=>Int)(a:Int,b:Int):Int=mapReduce(f,(x,y)=>x+y,0)(a,b)
  prod(x=>x)(1,5)
  sum(x=>x)(3,5)
}