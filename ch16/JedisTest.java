public class JedisTest {
	public static void main(String[] args) {
		Jedis jedis = new Jedis("192.168.2.128", 6379);
		// 测试
		System.out.println(jedis.ping());
		/* ========String======= */
		// 选择第0个数据库
		jedis.select(0);
		jedis.set("k1", "v1");
		jedis.setnx("k1", "value1");
		jedis.setex("k1", 2, "v3");
		// 在k1的值后面，拼接字符串hello
		jedis.append("k1", "hello");
		jedis.getrange("k1", 1, 2);
		String v1 = jedis.get("k1");
		Set<String>keys = jedis.keys("*");
		// 批量增加键值对
		jedis.mset("k1", "v1", "k2", "v2", "k3", "v3");
		// 批量获取键值对
		jedis.mget("k1", "k2", "k3");

		/* ========Key======= */
		// 判断某个键是否存在k1
		jedis.exists("k1");
		// 查看k1所存储的值的类型
		jedis.type("k1");
		// 设置 k1的过期时间为10s
		jedis.expire("k1", 5);
		// 移除 k1的生命周期
		jedis.persist("k1");
		// 查看 k1的剩余时间
		jedis.ttl("k1");
		// 删除k1
		jedis.del("k1");

		// 批量删除键值对
		jedis.del("k2", "k3");

		/* ========数字======= */
		jedis.set("k1", "1");
		// 把键k1的值+1
		jedis.incr("k1");
		// 把键k1的值-1
		jedis.decr("k1");
		// 把键k1的值+10
		jedis.incrBy("k1", 10);
		// 把键k1的值-10
		jedis.decrBy("k1", 10);

		/* ========List======= */
		jedis.lpush("key1", "v1", "v2", "v3");
		jedis.lpush("key2", "world");
		jedis.lpush("key2", "world");
		jedis.lpush("key2", "world");// 可以重复
		jedis.rpush("key2", "world");

		// 输出数据
		jedis.lrange("key1", 0, -1);
		jedis.lrange("key2", 0, 2);

		// 删除key2中的2个world值
		jedis.lrem("key2", 2, "world");
		// 删除第0个-第2个以外的集合元素
		jedis.ltrim("key2", 0, 2);
		// 出栈
		jedis.lpop("key2");
		jedis.rpop("key2");
		// 修改key2中第一个元素的value值
		jedis.lset("key1", 1, "newValue");
		// 获取集合key2中元素的个数
		jedis.llen("key2");
		// 获取集合key2中下标为1的元素
		jedis.lindex("key2", 1);

		jedis.lpush("myList", "4", "2", "0", "1", "3", "5");
		// 对myList进行排序
		jedis.sort("myList");

		// *========Hash=======*/
		Map<String, String>map = new HashMap<>();
		map.put("k1", "v1");
		map.put("k2", "v2");
		map.put("k3", "v3");
		// 向myHash中增加键值对
		jedis.hset("myHash", "k4", "4");
		// 向myHash中批量增加键值对
		jedis.hmset("myHash", map);
		// 获取myHash中的所有键值对
		jedis.hgetAll("myHash");
		// 获取myHash中的所有key
		jedis.hkeys("myHash");
		// 获取myHash中的所有value
		jedis.hvals("myHash");
		// 将k4的value增加10
		jedis.hincrBy("myHash", "k4", 10);
		// 将k4的value减少10
		jedis.hincrBy("myHash", "k4", 10);
		// 删除hash中的k4
		jedis.hdel("myHash", "k4");
		// 获取myHash中键值对的个数
		jedis.hlen("myHash");
		// 判断myHash中是否存在k4
		jedis.hexists("myHash", "k4");
		// 获取myHash中的一个值
		jedis.hmget("myHash", "k1");
		// 获取myHash中的多个值
		jedis.hmget("myHash", "k2", "k3");

		// *========Set=======*/
		jedis.sadd("ks1", "vs1", "vs2", "vs3", "vs4", "vs5");
		jedis.sadd("ks1", "vs6");

		// 获取ks1中的元素个数
		jedis.scard("ks1");
		// 获取ks1中的全部元素
		jedis.smembers("ks1");
		// 判断ks1中是否包含vs3元素
		jedis.sismember("ks1", "vs3");
		// 删除ks1中的元素:
		jedis.srem("ks1", "vs4");
		jedis.srem("ks1", "vs2", "vs3");
		// 随机删除ks1中的一个元素：
		jedis.spop("ks1");

		jedis.sadd("ks2", "hello", "world");
		// 将ks2中的hello移动到ks1中
		jedis.smove("ks2", "ks1", "hello");
		// 获取ks1和ks2的交集
		jedis.sinter("ks1", "ks2");
		// 获取ks1和ks2的并集
		jedis.sunion("ks1", "ks2");
		// 获取ks1减去ks2的差集
		jedis.sdiff("ks1", "ks2");

		// *========SortedSet(ZSet)=======*/
		Map<String, Double>map2 = new HashMap<String, Double>();
		map2.put("k1", 10.1);
		map2.put("k2", 2.2);
		map2.put("k3", 30.3);
		map2.put("k4", 4.4);
		// 向zset中增加带score值的元素
		jedis.zadd("zset", 12, "k5");
		jedis.zadd("zset", map2);
		// 获取zset中的所有元素（不带score值）
		jedis.zrange("zset", 0, -1);
		// 获取zset中的所有元素（带score值）
		jedis.zrangeWithScores("zset", 0, -1);
		jedis.zrangeByScore("zset", 0, 10);
		jedis.zrangeByScoreWithScores("zset", 0, 10);
		// 获取zset中k3的score值
		jedis.zscore("zset", "k3");
		// 获取zset中k2的排名
		jedis.zrank("zset", "k2");
		// 删除zset中的元素k3
		jedis.zrem("zset", "k3");
		// 获取zset中元素的个数
		jedis.zcard("zset");
		// 获取zset中score值在1-10之间的元素的个数
		jedis.zcount("zset", 1, 10);
		// 给zset中k2的score值加10
		jedis.zincrby("zset", 10, "k2");

		// *========事务=======*/
		// 监听k1
		jedis.watch("k1");
		// 开启事务
		Transaction tx = jedis.multi();
		// 注意，在事务中，不能使用jedis.set()设置值，而必须使用tx.set() ;
		tx.set("money", "1000");
		tx.incrBy("money", 200);
		tx.decrBy("money", 100);
		// 提交事务
		tx.exec();
		// 放弃事务
		// tx.discard();
		jedis.unwatch();
		// 清除数据
		jedis.flushDB();
	}
}