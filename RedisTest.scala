import com.redis.RedisClient

/**
 * Created by YD on 16/5/24.
 */
object RedisTest {
  def main(args: Array[String]) {
    val r = new RedisClient("192.168.99.100", 6379)
    r.set("key", "yoyoyo")
    r.get("key")
    //list
    r.lpush("list-1","foo")
    r.rpush("list-1", "bar")
  }
}
