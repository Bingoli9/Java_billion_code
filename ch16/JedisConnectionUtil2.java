package jedis;
import redis.clients.jedis.Jedis;
public class JedisConnectionUtil {
	private volatile static Jedis jedisInstance = null;
	private JedisConnectionUtil() {
	}
	// 获取Jedis对象（单例）
	public static Jedis getJedisInstance() {
		if (jedisInstance == null) {
			synchronized (Jedis.class) {
				if (jedisInstance == null)
					jedisInstance = new Jedis("192.168.2.128", 6379);
			}
		}
		return jedisInstance;
	}
	// 关闭Jedis对象
	public static void closeJedisInstance() {
		if (jedisInstance != null)
			jedisInstance.close();
	}
}