package test;

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
		Jedis jedis = new Jedis("192.168.16.40", 7000);
		jedis.set("name", "Jeff");
		jedis.close();
	}

	@Test
	public void shard() {
		List<JedisShardInfo> shardList = new ArrayList<>();
		shardList.add(new JedisShardInfo("192.168.16.40", 7000));
		shardList.add(new JedisShardInfo("192.168.16.40", 7001));
		shardList.add(new JedisShardInfo("192.168.16.40", 7002));

		ShardedJedis jedis = new ShardedJedis(shardList);
		for (int i = 0; i <= 9; i++) {
			jedis.set("name"+i, "Jeff"+i);
		}
		

	}
}

