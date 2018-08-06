package redis;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;

public class TestJedis {
	// 用Jedis操作redis节点 set/get
	// @Test
	public void redis() {
		Jedis jedis = new Jedis("192.168.16.30", 6379);
		jedis.set("name", "jeff");
		jedis.close();
	}

	@Test
	public void shard() {
		List<JedisShardInfo> shardList = new ArrayList<>();
		shardList.add(new JedisShardInfo("192.168.16.30", 6379));
		shardList.add(new JedisShardInfo("192.168.16.30", 6380));
		//shardList.add(new JedisShardInfo("192.168.16.30", 6381));

		ShardedJedis jedis = new ShardedJedis(shardList);
		for (int i = 0; i <= 9; i++) {
			jedis.set("name"+i, "Jeff"+i);
		}
		jedis.close();

	}
}
