/**
 * Created by YD on 16/2/17.
 */
object mouseTracking_Json2CSV {
  import spray.json._
  import spray.json.DefaultJsonProtocol
  case class mouseTracking(windowSizeWidth:Int, ip:String, ele_value:String, ele_selector:String,
                           url:String, event:String, mousePosY:Int, version_server:String, version_client:String,
                           userAgent:String, version:String, client:String, windowSizeHeight:Int, datetimeClient:String,
                           referrer:String, datetimeServer:String, ele_class:String, mousePosX:Int, ele_id:String)
  object YDJsonProtocol extends DefaultJsonProtocol{
    implicit val mouseFormat = jsonFormat19(mouseTracking)
  }


  def main(args: Array[String]) {
    import scala.io.Source
    val source = Source.fromFile("/Users/apple/Desktop/1477.txt").getLines.toArray//read source
    import java.io.File
    val f = new File("/Users/apple/Desktop/out.csv") //create csv file
    import com.github.tototoshi.csv._
    val writer = CSVWriter.open(f)
    writer.writeRow(List("windowSizeWidth",
      "ip",
      "ele_value",
      "ele_selector",
      "url",
      "event",
      "mousePosY",
      "version_server",
      "version_client",
      "userAgent",
      "version",
      "client",
      "windowSizeHeight",
      "datetimeClient",
      "referrer",
      "datetimeServer",
      "ele_class",
      "mousePosX",
      "ele_id"))//header
    for(i <- 0.until(source.length)){
      import YDJsonProtocol._
      val tempjsonAst = source(i).parseJson //parseJson Protocol
      val tempObject = tempjsonAst.convertTo[mouseTracking]
      writer.writeRow(List(tempObject.windowSizeWidth,
        tempObject.ip,
        tempObject.ele_value,
        tempObject.ele_selector,
        tempObject.url,
        tempObject.event,
        tempObject.mousePosY,
        tempObject.version_server,
        tempObject.version_client,
        tempObject.userAgent,
        tempObject.version,
        tempObject.client,
        tempObject.windowSizeHeight,
        tempObject.datetimeClient,
        tempObject.referrer,
        tempObject.datetimeServer,
        tempObject.ele_class,
        tempObject.mousePosX,
        tempObject.ele_id
      ))
    }
    writer.close()
  }

}
