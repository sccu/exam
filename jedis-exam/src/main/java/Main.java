import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created with IntelliJ IDEA.
 * User: sccu
 * Date: 2014. 3. 11.
 * Time: 14:17
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args) {
        JedisPool pool = new JedisPool(new JedisPoolConfig(), "172.21.0.21", 9200, 1000);
        Jedis jedis = pool.getResource();

    }
}
