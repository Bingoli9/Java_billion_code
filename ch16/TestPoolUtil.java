import java.util.UUID;
import redis.clients.jedis.Jedis;
public class TestPoolUtil {
	public static void main(String[] args) {
		// 初始化连接池
		RedisPoolUtil.initialPool();
		// 不断地创建新线程，并让每个线程获取Redis连接	
	while (true) {
			new Thread(() -> {
				Jedis jedis = RedisPoolUtil.getConn();
				String key = "key-" +(int)(( Math.random()*1000)+9000);//key-四位随机数
				String value = UUID.randomUUID().toString();
				jedis.set(key, value);
				try {
					// 模拟每个线程随机执行一段时间
					Thread.sleep((int) (Math.random() * 2000));
					System.out.println("key=" + key + ",value=" + value);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					RedisPoolUtil.closeConn();
				}
			}).start();
		}
	}
}