public class  JedisConnectionUtil3 {	
		//非static共享变量
	private  Jedis jedisInstance = null;
	// 非static方法
	public  Jedis getJedisInstance() throws Exception{
		if (jedisInstance == null)
					jedisInstance = new Jedis("192.168.2.128", 6379);
	return jedisInstance;
	     }
	// 非static方法
	public void closeConnection() throws Exception{
		if (jedisInstance != null)
				jedisInstance.close();
	     }
}

class RedisTransaction{
		//模拟转账事务
		public void transportMoney() throws Exception {
			JedisConnectionUtiljedisUtil = newJedisConnectionUtil();
			Jedis jedis = jedisUtil.getJedisInstance() ;
			jedis.watch("k1");
			// 开启事务
			Transaction tx = jedis.multi();
			tx.set("money", "1000");
			tx.incrBy("money", 200);
			tx.decrBy("money", 100);
			// 提交事务
			tx.exec();
			jedis.unwatch();
jedis.close();
		}
}