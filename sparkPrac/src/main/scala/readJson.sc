import org.apache.spark.{SparkConf, SparkContext}
import sqlContext.implicits._
import org.apache.spark.sql._
{
  val sc = new SparkContext(new SparkConf().setAppName("sparkPrac").setMaster("local[*]"))

  val sqlContext = new org.apache.spark.sql.SQLContext(sc)

  val df = sqlContext.read.json("/Users/apple/Desktop/betterfilesData.json")

  df.show()


}