/**
 * Created by YD on 16/2/19.
 */
import scala.io.Source
import spray.json._
import java.io.File
import com.github.tototoshi.csv._

object mouseTracking_Json2CSVmatchK {

  def main(args: Array[String]) {

    val source = Source.fromFile("/Users/apple/Desktop/wrong.txt").getLines.toArray
    val f = new File("/Users/apple/Desktop/out.csv")
    val writer = CSVWriter.open(f)
    writer.writeRow(List("event","url","ele_selector","ele_id","ele_class","ele_value",
      "ip","windowWidth","windowHeight","mousePosX","mousePosY",
      "datetimeClient","datetimeServer","referrer","userAgent","version","version_client",
      "version_server","country_name","city_name","latitude","longtitude","cdihash"))//header

    //for loop
    for(i<- 0.until(source.length)){
      val jsonMap = JsonParser(source(i)).asJsObject.fields
      val tempList=matchK(jsonMap)
      writer.writeRow(tempList)
    }

    writer.close()

  }

  def matchK(jsonSingleRecord:Map[String,spray.json.JsValue]): List[Any] = {
    val temp_event = jsonSingleRecord.get("event").get
    val temp_url = jsonSingleRecord.get("url").getOrElse("missing")
    val temp_ele_selector = jsonSingleRecord.get("ele_selector").getOrElse("missing")
    val temp_ele_id = jsonSingleRecord.get("ele_id").getOrElse("missing")
    val temp_ele_class = jsonSingleRecord.get("ele_class").getOrElse("missing")
    val temp_ele_value = jsonSingleRecord.get("ele_value").getOrElse("missing")
    val temp_ip = jsonSingleRecord.get("ip").getOrElse("255.255.255.255")


    //處理nested json的狀況
    val tempSizeWidth = if (jsonSingleRecord.get("windowSize") == None) -1
    else {
      val sepSize = jsonSingleRecord.get("windowSize").get.toString
      val sepSizeRecord = JsonParser(sepSize).asJsObject.fields
      sepSizeRecord.get("width").getOrElse(-1)
    }

    val tempSizeHeight = if (jsonSingleRecord.get("windowSize") == None) -1
    else {
      val sepSize = jsonSingleRecord.get("windowSize").get.toString
      val sepSizeRecord = JsonParser(sepSize).asJsObject.fields
      sepSizeRecord.get("height").getOrElse(-1)
    }

    val tempmousePosX = if (jsonSingleRecord.get("mousePos") == None) -1
    else {
      val sepPos = jsonSingleRecord.get("mousePos").get.toString
      val sepPosRecord = JsonParser(sepPos).asJsObject.fields
      sepPosRecord.get("x").getOrElse(-1)
    }

    val tempmousePosY = if (jsonSingleRecord.get("mousePos") == None) -1
    else {
      val sepPos = jsonSingleRecord.get("mousePos").get.toString
      val sepPosRecord = JsonParser(sepPos).asJsObject.fields
      sepPosRecord.get("y").getOrElse(-1)
    }

    val temp_datetimeClient = jsonSingleRecord.get("datetimeClient").getOrElse("1970-01-01 00:00:00.000+0000")
    val temp_datetimeServer = jsonSingleRecord.get("datetimeServer").getOrElse("1970-01-01 00:00:00.000+0000")
    val temp_referrer = jsonSingleRecord.get("referrer").getOrElse("missing")
    val temp_userAgent = jsonSingleRecord.get("userAgent").getOrElse("missing")
    val temp_version = jsonSingleRecord.get("version").getOrElse("0.0.0")
    val temp_version_client = jsonSingleRecord.get("version_client").getOrElse("0.0.0")
    val temp_version_server = jsonSingleRecord.get("version_server").getOrElse("0.0.0")
    val temp_country_name = jsonSingleRecord.get("country_name").getOrElse("missing")
    val temp_city_name = jsonSingleRecord.get("city_name").getOrElse("missing")
    val temp_latitude = jsonSingleRecord.get("latitude").getOrElse(999)
    val temp_longtitude = jsonSingleRecord.get("longtitude").getOrElse(999)
    val temp_cdihash = jsonSingleRecord.get("cdihash").getOrElse("missing")
    List(temp_event,temp_url,temp_ele_selector,temp_ele_id,temp_ele_class,temp_ele_value,
      temp_ip,tempSizeWidth,tempSizeHeight,tempmousePosX,tempmousePosY,
      temp_datetimeClient,temp_datetimeServer,temp_referrer,temp_userAgent,temp_version,temp_version_client,
      temp_version_server,temp_country_name,temp_city_name,temp_latitude,temp_longtitude,temp_cdihash)
  }

}
