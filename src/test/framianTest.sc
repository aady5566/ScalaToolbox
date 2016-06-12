import java.io.{PrintWriter, File}
import framian.{Cols, Frame}
import framian.csv.{CsvFormat, Csv}
/*{
  case class Company(name: String, exchange: String, ticker: String, currency: String, marketCap: BigDecimal)
  val Acme = Company("Acme", "NASDAQ", "ACME", "USD", BigDecimal("123.00"))
  val BobCo = Company("Bob Company", "NASDAQ", "BOB", "USD", BigDecimal("45.67"))
  val Cruddy = Company("Cruddy Inc", "XETRA", "CRUD", "EUR", BigDecimal("1.00"))
  val dt = Frame.fromRows(Acme, BobCo, Cruddy)
  println(dt)
}*/

val df=Csv.parseFile(new File("/Users/apple/Desktop/test.csv")).labeled.toFrame
println(""+df.rows+" "+df.cols)
val df2=df.filter(Cols("EngineSize").as[Double])( _ <= 4.0 )
println(""+df2.rows+" "+df2.cols)
val df3=df2.map(Cols("Weight").as[Int],"WeightKG")(r=>r.toDouble*0.453592)
println(""+df3.rows+" "+df3.cols)
println(df3.colIndex)
val csv = Csv.fromFrame(new CsvFormat(",", header = true))(df3)
new PrintWriter("/Users/apple/Desktop/out.csv") { write(csv.toString); close }
