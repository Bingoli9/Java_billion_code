public class JedisMasterSlave {
    public static void main(String[] args) throws Exception {
        /*
            提示：通过网络远程访问redis时，需要在redis配置文件中绑定服务器的ip地址。
             一般而言，为了能同时能让本机和远端访问redis，建议对配置文件（如redis.conf、
redis1.conf、redis2.conf）修改如下：
                #bind  本机IP  本机在网络中的IP地址，如下形式
                bind 127.0.0.1 192.168.2.130
         */    
        Jedis master = new Jedis("192.168.2.129", 6379);
        Jedis Slave1 = new Jedis("192.168.2.130", 7001);
        Jedis Slave2 = new Jedis("192.168.2.130", 7001);

        Slave1.slaveof("192.168.2.129", 6379);
        Slave2.slaveof("192.168.2.129", 6379);

        master.set("hello", "redis");
        //Java代码是在内存中执行，速度较快；而Redis在主从同步有一定的IO延迟，
因此本测试给予了一定的时间延迟
        Thread.sleep(2000);

        String result1 = Slave1.get("hello");
        String result2 = Slave1.get("hello");
        System.out.println(result1 + "---"+ result2);
    }
}