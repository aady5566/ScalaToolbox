/**
  * Created by YD on 2016/6/4.
  */
import better.files.File
import com.redis.RedisClient
import spray.json.JsonParser
import com.roundeights.hasher.Implicits._
import scala.language.postfixOps
import net.bmjames.opts._
import scalaz.{Applicative, Validation, ValidationNel, Success, Failure}
import scalaz.syntax.apply._

object Redis2getOpts {

  case class UserData(inputPath: String,outputPath: String)
  type V[A] = ValidationNel[String, A]
  type ParserV[A] = Parser[V[A]]

  def validInput(inputPath: String): V[String] =
    if (inputPath.length>0) Validation.success(inputPath)
    else Validation.failureNel("Plz insert the input Path!")

  def validOutput(OutputPath: String): V[String] =
    if (OutputPath.length>0) Validation.success(OutputPath)
    else Validation.failureNel("Plz insert the output Path!")

  implicit val ParserVInstance: Applicative[ParserV] =
    Applicative[Parser] compose Applicative[V]

  val inputPath: ParserV[String] = strOption(short('i'), long("inputPath")).map(validInput)
  val outputPath: ParserV[String] = strOption(short('o'), long("outputPath")).map(validOutput)
  val userData: ParserV[UserData] = ^(inputPath, outputPath)(UserData)

  def main(args: Array[String]): Unit = {
    val validatedUserData = execParser(args, "ValidationExample", info(userData))
    validatedUserData match {

      case Success(UserData(i, o)) =>
        println(s"Successfully insert input: <$i>; output: <$o>");
        val f = File(i)
        val files = f.list.toSeq
        val redis = new RedisClient(o, 6379) //connect to the Client
        redis.flushall //flushall in the db
        //for(idx <- 0 until files.length; if idx % 2 ==0){//for the purpose of skipping the hidden files
        for(idx <- 0 until files.length){
        val tempFile = File(files(idx).toString).contentAsString
          val m = JsonParser(tempFile).asJsObject.fields
          val r =  new scala.util.Random(idx)//set seed to 1
          val salt = r.alphanumeric.take(5).mkString//length for 5 chars
          val binded = m("password").toString.replace("\"","")+salt
          println(binded)
          val hash = binded.md5.hex //hash function
          redis.hmset(m("account").toString.replace("\"",""),Map("account" -> m("account").toString().replace("\"",""),"password" -> hash , "salt" -> salt))
          println(redis.keys())//list all keys
        }
        redis.disconnect

      case Failure(errors) =>
        errors.list.foreach(System.err.println)
        System.exit(1)
    }
  }
}
