public class JedisUtil {
	private static Jedis jedisInstance = null;
           //获取一个Jedis连接对象
	public static Jedis getJedisInstance() throws Exception{
		if (jedisInstance == null)
					jedisInstance = new Jedis("192.168.2.128", 6379);
	return jedisInstance;
	     }
	//关闭Jedis连接对象
	public static void closeConnection() throws Exception{
		if (jedisInstance != null)
				jedisInstance.close();
	     }
}