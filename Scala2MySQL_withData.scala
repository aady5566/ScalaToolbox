/**
 * Created by YD on 15/9/16.
 */
//libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.36"
import java.sql.{ResultSet, DriverManager}

object mysql0916v2 {
    def main(args: Array[String]) {
      val driver = "com.mysql.jdbc.Driver"
      //   <ip><port>/schema(database)name
      val url = "jdbc:mysql://192.168.99.100:3306/usa"
      val username = "root"
      val password = "1234567"
      // create database connection
      //val dbc = "jdbc:mysql://192.168.99.100:3306/mysql?user=root&password=1234567"
      //load the driver
      Class.forName(driver)
      //classOf[com.mysql.jdbc.Driver]
      //setup the connection
      val conn = DriverManager.getConnection(url, username, password)
      //val conn = DriverManager.getConnection(dbc)
      val statement = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE)
      //val statement= conn.createStatement()
      // do database insert
      try {
        val data = new cleanData
        val prep = conn.prepareStatement("INSERT INTO noid (UsafId,WbanId,date,time,latitude,longtitude,elevation,windDirection,atmosPressure) VALUES (?,?,?,?,?,?,?,?,?) ")
        for (a <- 0 to (data.date.length-1)){
          prep.setString(1,data.UsafId(a))
          prep.setString(2,data.WbanId(a))
          prep.setString(3,data.date(a))
          prep.setString(4,data.time(a))
          prep.setString(5,data.latitude(a))
          prep.setString(6,data.longtitude(a))
          prep.setString(7,data.elevation(a))
          prep.setString(8,data.windDirection(a))
          prep.setString(9,data.atmosPressure(a))
          prep.executeUpdate
        }
      }
      finally {
        conn.close
      }
    }
  class cleanData extends Serializable {
    //  val source = scala.io.Source.fromFile("/Users/apple/Desktop/1917").getLines.toList
    val UsafId = clean(4,10)
    val WbanId =clean(10,15)
    val date =clean(15,23)
    val time =clean(23,27)
    val latitude =clean(28,34)
    val longtitude=clean(34,41)
    val elevation =clean(46,51)
    val windDirection=clean(60,63)
    val atmosPressure=clean(110,116)
    def clean(start:Int, end:Int):List[String] = {
      var source = scala.io.Source.fromFile("/Users/apple/Desktop/1917").getLines.toList
      /**
       * getLines method treats “any of \r\n, \r, or \n as a line separator (longest match)
       */
      if (source.forall(_.length>=116)==false){
        //      var source = scala.io.Source.fromFile("/Users/apple/Desktop/1917").getLines.toList
        var newsource=source.filter(_.length>=116)
        print("Deal with StringIndexOutOfBoundsException!")
        val value=newsource.map(_.substring(start,end))
        value
      }
      else{
        /**
         * substring(start,end(without))
         */
        val value=source.map(_.substring(start,end))
        value
      }
    }
  }
}
