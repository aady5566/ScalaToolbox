/**
 * Created by YD on 15/9/24.
 */
//libraryDependencies += "org.apache.avro" % "avro" % "1.7.7"
import java.io.File
import org.apache.avro.Schema
import org.apache.avro.file.{DataFileReader, DataFileWriter}
import org.apache.avro.generic.{GenericDatumReader, GenericRecord, GenericDatumWriter}


object avro0924 {
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
  def main(args: Array[String]) {
    //data-import
    val data= new cleanData
    //data  to avro
    val schemaTemplate="{\n    \"namespace\": \"weather.avro\",\n    \"type\": \"record\",\n    \"name\": \"YD\",\n    \"fields\": [\n        {\n            \"name\": \"UsafId\",\n            \"type\": [\n                \"string\",\n                \"null\"\n            ]\n        },\n        {\n            \"name\": \"WbanId\",\n            \"type\": [\n                \"string\",\n                \"null\"\n            ]\n        },\n        {\n            \"name\": \"date\",\n            \"type\": [\n                \"string\",\n                \"null\"\n            ]\n        },\n        {\n            \"name\": \"time\",\n            \"type\": [\n                \"string\",\n                \"null\"\n            ]\n        },\n        {\n            \"name\": \"latitude\",\n            \"type\": [\n                \"string\",\n                \"null\"\n            ]\n        },\n        {\n            \"name\": \"longtitude\",\n            \"type\": [\n                \"string\",\n                \"null\"\n            ]\n        },\n        {\n            \"name\": \"elevation\",\n            \"type\": [\n                \"string\",\n                \"null\"\n            ]\n        },\n        {\n            \"name\": \"windDirection\",\n            \"type\": [\n                \"string\",\n                \"null\"\n            ]\n        },\n        {\n            \"name\": \"atmosPressure\",\n            \"type\": [\n                \"string\",\n                \"null\"\n            ]\n        }\n    ]\n}"
    val schema = new Schema.Parser().parse(schemaTemplate);

    //serializing
    //new a file
    val file= new File("weather.avro")
    //schema write-in
    val datumWriter = new GenericDatumWriter[GenericRecord](schema);
    //data write-in
    val dataFileWriter = new DataFileWriter[GenericRecord](datumWriter);
    dataFileWriter.create(schema, file);

    //Creating object using schema
    var avroRec=new org.apache.avro.generic.GenericData.Record(schema);
    for (idx <-0 until data.date.length){
      avroRec.put("UsafId", data.UsafId(idx));
      avroRec.put("WbanId", data.WbanId(idx));
      avroRec.put("date", data.date(idx));
      avroRec.put("time", data.time(idx));
      avroRec.put("latitude", data.latitude(idx));
      avroRec.put("longtitude", data.longtitude(idx));
      avroRec.put("elevation", data.elevation(idx));
      avroRec.put("windDirection", data.windDirection(idx));
      avroRec.put("atmosPressure", data.atmosPressure(idx));
      //data append to file
      dataFileWriter.append(avroRec);
    }
    dataFileWriter.close();

    //Deserializing
    val datumReader = new GenericDatumReader[GenericRecord](schema);
    val dataFileReader = new DataFileReader[GenericRecord](file, datumReader);
    while (dataFileReader.hasNext()) {
      // Reuse user object by passing it to next(). This saves us from
      // allocating and garbage collecting many objects for files with
      // many itemss.
      //  var user = dataFileReader.next();
      println(dataFileReader.next());
    }

  }
}
