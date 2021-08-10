public class RedisPoolUtil {
	// redis的连接池对象
	private static JedisPool jedisPool = null;
	// redis配置文件
	private static final StringREDIS_CONFIG = "redis.properties";
	// 使用ThreadLocal确保：多个redis线程使用的是同一个redis连接。
	private static ThreadLocal<Jedis>local = new ThreadLocal<Jedis>();

	// 初始化连接
	public static void initialPool() {
		try {
			Properties props = new Properties();
			// 读取连接池配置信息
			    props.load(RedisPoolUtil.class.getClassLoader()
.getResourceAsStream(REDIS_CONFIG));
			// 配置jedis连接池
			JedisPoolConfig config = new JedisPoolConfig();
			// 设置连接池的配置参数
			config.setMaxTotal(Integer.valueOf(props.getProperty("jedis.pool.maxActive")));
			config.setMaxIdle(Integer.valueOf(props.getProperty("jedis.pool.maxIdle")));
			config.setMaxWaitMillis(Long.valueOf(props.getProperty("jedis.pool.maxWait")));
			// 根据配置实例化jedis连接池
			jedisPool = new JedisPool(config, props.getProperty("redis.ip"),
					Integer.valueOf(props.getProperty("redis.port")),
					Integer.valueOf(props.getProperty("redis.timeout"))
			// ,props.getProperty("redis.passWord") 如果redis服务设置了密码，则打开此注释
			);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 获取连接
	public static Jedis getConn() {
		Jedis jedis = local.get();

		if (jedis == null) {
			if (jedisPool == null) {
				initialPool();
			}
			jedis = jedisPool.getResource();
			local.set(jedis);
		}
		return jedis;
	}

	// 归还连接
	public static void closeConn() {
		// 从本地连接池中获取
		Jedis jedis = local.get();
		if (jedis != null) {
			jedis.close();
		}
		local.set(null);
	}

	// 关闭连接池
	public static void closePool() {
		if (jedisPool != null) {
			jedisPool.close();
		}
	}
}