package cn.advu.workflow.common.cache;

import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import redis.clients.jedis.Jedis;

import java.util.*;
import java.util.Map.Entry;

/**
 * RedisClient适配器，包含了对redis对象的基本操作，如Key、String、List、Hash、Set、SortedSet的操作
 *
 * @author DIKEPU
 * @author NIUQIANGHONG
 *
 */
//@Component("redisAdapter")
public class RedisClientAdapter {

	@Autowired
	private RedisClient redisClient;

	// ################## Key start ##################
	/**
	 * 是否存在key
	 *
	 * @param key
	 *            不能为空
	 * @return
	 */
	public boolean existsKey(final String key) {
		Assert.hasText(key, "key 不能为空或null");
		return redisClient.exec(new ExecCaller<Boolean>() {
			@Override
			public Boolean exec(Jedis jedis) {
				return jedis.exists(key);
			}

		});
	}

	/**
	 * key剩余seconds时间，-1为不限时间
	 * @param key
	 * @return
	 */
	public Long ttl(final String key) {
		Assert.hasText(key, "key 不能为空或null");
		return redisClient.exec(new ExecCaller<Long>() {
			@Override
			public Long exec(Jedis jedis) {
				return jedis.ttl(key);
			}

		});
	}

	/**
	 * 返回匹配pattern的key集合
	 *
	 * @param pattern
	 *            匹配字符串
	 * @return
	 */
	public List<String> findKeys(final String pattern) {
		Assert.hasText(pattern, "pattern 不能为空或null");
		return redisClient.exec(new ExecCaller<List<String>>() {
			@Override
			public List<String> exec(Jedis jedis) {
				return new ArrayList<>(jedis.keys(pattern));
			}
		});
	}

	/**
	 * 对特殊的key设置过期时间，过期时间{@link RedisExpireTime}
	 *
	 * @param key
	 * @param seconds
	 * @return
	 */
	public boolean expireKey(final String key, final int seconds) {
		Assert.hasText(key, "key 不能为空或null");
		return redisClient.exec(new ExecCaller<Boolean>() {
			@Override
			public Boolean exec(Jedis jedis) {
				Long expire = jedis.expire(key, seconds);
				return expire == null ? false : expire >= 0;
			}

		});
	}

	/**
	 * 删除key
	 *
	 * @param key
	 * @return 删除个数
	 */
	public long delKeys(final String... keys) {
		Assert.notEmpty(keys, "keys 不能为null，至少有一个元素");
		return redisClient.exec(new ExecCaller<Long>() {
			@Override
			public Long exec(Jedis jedis) {
				return jedis.del(keys);
			}
		});
	}

	// ################## Key end ##################

	// ################## String start ##################

	/**
	 *
	 * @param key
	 * @param value
	 */
	public void strSet(final String key, final String value) {
		Assert.hasText(key, "key 不能为空或null");
		Assert.hasText(value, "value 不能为空或null");
		redisClient.set(key, value);
	}

	/**
	 * 关联整型数值
	 * @param key
	 * @param value
	 */
	public void strSet(final String key, final int value) {
		Assert.hasText(key, "key 不能为空或null");
		redisClient.set(key, value);
	}

	/**
	 * 关联整型数值
	 * @param key
	 * @param value
	 */
	public void strSet(final String key, final long value) {
		Assert.hasText(key, "key 不能为空或null");
		redisClient.set(key, value);
	}

	public void strSetEx(final String key, final String value, int seconds) {
		Assert.hasText(key, "key 不能为空或null");
		Assert.hasText(value, "value 不能为空或null");
		redisClient.setEx(key, value, seconds);
	}

	/**
	 *  根据主键返回字符串，如果不存在返回null
	 * @param key
	 * @return 字符串
	 */
	public String strGet(final String key) {
		Assert.hasText(key, "key 不能为空或null");
		return redisClient.getStr(key);
	}

	/**
	 * 返回加一后的值，如果key不存在默认其为0返回1
	 *
	 * @param key
	 * @return
	 */
	public long strIncr(final String key) {
		Assert.hasText(key, "key 不能为空或null");
		return redisClient.exec(new ExecCaller<Long>() {
			@Override
			public Long exec(Jedis jedis) {
				return jedis.incr(key);
			}
		});
	}

	/**
	 * 将 key 所储存的值加上增量inc，如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 INCRBY 命令。
	 * @param key
	 * @param inc
	 * @return 增加inc后的值
	 */
	public long strIncrBy(final String key, final long inc) {
		Assert.hasText(key, "key 不能为空或null");
		return redisClient.exec(new ExecCaller<Long>() {
			@Override
			public Long exec(Jedis jedis) {
				return jedis.incrBy(key, inc);
			}
		});
	}

	public double strIncrByFloat(final String key, final double inc) {
		Assert.hasText(key, "key 不能为空或null");
		return redisClient.exec(new ExecCaller<Double>() {
			@Override
			public Double exec(Jedis jedis) {
				return jedis.incrByFloat(key, inc);
			}
		});
	}

	public long strDecr(final String key) {
		Assert.hasText(key, "key 不能为空或null");
		return redisClient.exec(new ExecCaller<Long>() {
			@Override
			public Long exec(Jedis jedis) {
				return jedis.decr(key);
			}
		});
	}

	public long strDecrBy(final String key, final long dec) {
		Assert.hasText(key, "key 不能为空或null");
		return redisClient.exec(new ExecCaller<Long>() {
			@Override
			public Long exec(Jedis jedis) {
				return jedis.decrBy(key, dec);
			}
		});
	}

	// ################## String end ##################

	// ################## Hash start ##################

	public Map<String, String> hashGetAll(final String key) {
		Assert.hasText(key, "key 不能为空或null");
		return redisClient.exec(new ExecCaller<Map<String, String>>() {
			@Override
			public Map<String, String> exec(Jedis jedis) {
				return jedis.hgetAll(key);
			}
		});
	}

	/**
	 * key 不存在返回null
	 *
	 * @param key
	 * @param fields
	 * @return
	 */
	public Map<String, String> hashMGet(final String key,
										final String... fields) {
		Assert.hasText(key, "key 不能为空或null");
		Assert.notEmpty(fields, "keys 不能为null，至少有一个元素");
		return redisClient.exec(new ExecCaller<Map<String, String>>() {
			@Override
			public Map<String, String> exec(Jedis jedis) {
				List<String> results = jedis.hmget(key, fields);
				if (CollectionUtils.isNotEmpty(results)) {
					Map<String, String> map = new HashMap<String, String>();
					for (int i = 0; i < fields.length; i++) {
						if (results.get(i) != null) {
							map.put(fields[i], results.get(i));
						}
					}
					return map;
				}

				return null;
			}
		});
	}

	public List<String> hashMGetList(final String key, final String... fields) {
		Assert.hasText(key, "key 不能为空或null");
		Assert.notEmpty(fields, "fields 不能为null，至少有一个元素");
		return redisClient.exec(new ExecCaller<List<String>>() {
			@Override
			public List<String> exec(Jedis jedis) {
				return jedis.hmget(key, fields);
			}
		});
	}

	/**
	 * set给当前的key hash值，会替换原来的旧值
	 *
	 * @param key
	 * @param hash
	 * @return 返回 OK 或 Exception如果hash为空
	 *
	 */
	public <V> String hashMSet(final String key, final Map<String, V> hash) {
		Assert.hasText(key, "key 不能为空或null");
		Assert.notEmpty(hash, "hash不能为空");
		return redisClient.exec(new ExecCaller<String>() {
			@Override
			public String exec(Jedis jedis) {
				Iterator<Entry<String, V>> it = hash.entrySet().iterator();
				Map<String, String> map = new HashMap<String, String>();
				while (it.hasNext()) {
					Entry<String, V> entry = it.next();
					map.put(entry.getKey(), entry.getValue() == null ? ""
							: String.valueOf(entry.getValue()));
				}
				return jedis.hmset(key, map);
			}
		});

	}

	public long hashDel(final String key, final String... fields) {
		Assert.hasText(key, "key 不能为空或null");
		Assert.notEmpty(fields, "fields 不能为null，至少有一个元素");
		return redisClient.exec(new ExecCaller<Long>() {
			@Override
			public Long exec(Jedis jedis) {
				return jedis.hdel(key, fields);
			}
		});
	}

	public long hashIncrBy(final String key, final String field, final long inc) {
		Assert.hasText(key, "key 不能为空或null");
		Assert.hasText(field, "field 不能为空或null");
		return redisClient.exec(new ExecCaller<Long>() {
			@Override
			public Long exec(Jedis jedis) {
				return jedis.hincrBy(key, field, inc);
			}
		});
	}

	// ################## Hash end ##################

	// ################## List start ##################
	// 返回全部 左右加入 trim 长度

	/**
	 * 根据begin和end范围查询并返回集合。
	 * <p>
	 * 查询全部list，begin 0 end -1。
	 *
	 * @param key
	 * @param begin
	 * @param end
	 */
	public List<String> listRange(final String key, final long begin,
								  final long end) {
		Assert.hasText(key, "key 不能为空或null");
		return redisClient.exec(new ExecCaller<List<String>>() {
			@Override
			public List<String> exec(Jedis jedis) {
				return jedis.lrange(key, begin, end);
			}
		});

	}

	/**
	 * 对一个列表进行修剪(trim)，就是说，让列表只保留指定区间内的元素，不在指定区间之内的元素都将被删除。 举个例子，执行命令 LTRIM list
	 * 0 2 ，表示只保留列表 list 的前三个元素，其余元素全部删除。
	 *
	 * @param key
	 * @param begin
	 * @param end
	 *            (闭区间)
	 * @return
	 */
	public String listTrim(final String key, final long begin, final long end) {
		Assert.hasText(key, "key 不能为空或null");
		return redisClient.exec(new ExecCaller<String>() {
			@Override
			public String exec(Jedis jedis) {
				return jedis.ltrim(key, begin, end);
			}
		});

	}

	public long listLen(final String key) {
		Assert.hasText(key, "key 不能为空或null");
		return redisClient.exec(new ExecCaller<Long>() {
			@Override
			public Long exec(Jedis jedis) {
				return jedis.llen(key);
			}
		});
	}

	/**
	 * 向列表中添加元素
	 * @param key
	 * @param value
	 */
	public void listPush(final String key, final String value) {
		Assert.hasText(key, "key 不能为空或null");
		redisClient.exec(new ExecCaller<Object>() {
			@Override
			public Object exec(Jedis jedis) {
				jedis.rpush(key, value);
				return null;
			}
		});
	}

	/**
	 * 在指定Key关联的链表中，删除值等于value的元素
	 * @param key
	 * @param count
	 * @param value
	 * @return 被删除的元素数量
	 */
	public long listRem(final String key, final String value) {
		Assert.hasText(key, "key 不能为空或null");
		return redisClient.exec(new ExecCaller<Long>() {
			@Override
			public Long exec(Jedis jedis) {
				return jedis.lrem(key, 0, value);
			}
		});
	}

	// ################## List end ##################

	// ################## Set start ##################
	// TODO set 差集交集并集使用时请实现
	/**
	 * set集合增加成员
	 * @param key
	 * @param members
	 * @return
	 */
	public long setAdd(final String key, final String... members) {
		Assert.hasText(key, "key 不能为空或null");
		Assert.notEmpty(members, "values 不能为null，至少有一个元素");
		return redisClient.exec(new ExecCaller<Long>() {
			@Override
			public Long exec(Jedis jedis) {
				return jedis.sadd(key, members);
			}
		});
	}

	/**
	 * 成员数量
	 *
	 * @param key
	 * @return
	 */
	public String setPop(final String key) {
		Assert.hasText(key, "key 不能为空或null");
		return redisClient.exec(new ExecCaller<String>() {
			@Override
			public String exec(Jedis jedis) {
				return jedis.spop(key);
			}
		});
	}
	/**
	 * 成员数量
	 *
	 * @param key
	 * @return
	 */
	public long setCard(final String key) {
		Assert.hasText(key, "key 不能为空或null");
		return redisClient.exec(new ExecCaller<Long>() {
			@Override
			public Long exec(Jedis jedis) {
				return jedis.scard(key);
			}
		});
	}

	/**
	 * 删除成员
	 * @param key
	 * @param members
	 * @return
	 */
	public long setRem(final String key, final String... members) {
		Assert.hasText(key, "key 不能为空或null");
		return redisClient.exec(new ExecCaller<Long>() {
			@Override
			public Long exec(Jedis jedis) {
				return jedis.srem(key, members);
			}
		});
	}
	/**
	 * 是否是该成员
	 * @param key
	 * @param member
	 * @return
	 */
	public boolean setIsMember(final String key, final String member) {
		Assert.hasText(key, "key 不能为空或null");
		Assert.hasText(member, "member 不能为空或null");
		return redisClient.exec(new ExecCaller<Boolean>() {
			@Override
			public Boolean exec(Jedis jedis) {
				return jedis.sismember(key, member);
			}
		});
	}
	// ################## ZSet start ##################
	/**
	 * 将一个 member 元素及其 score 值加入到有序集 key 当中
	 *
	 * @param key
	 * @param score
	 * @param member
	 * @return
	 */
	public long zsetAdd(final String key, final double score,
						final String member) {
		Assert.hasText(key, "key 不能为空或null");
		Assert.hasText(member, "member 不能为空或null");
		return redisClient.exec(new ExecCaller<Long>() {
			@Override
			public Long exec(Jedis jedis) {
				return jedis.zadd(key, score, member);
			}
		});

	}

	/**
	 * 返回名称为key的zset中的index从start到end的所有元素(从大到小排序)
	 *
	 * @param key
	 *            zset的key
	 * @param start
	 *            起始索引
	 * @param end
	 *            结束索引, -1表示返回全部元素
	 * @return
	 */
	public Set<String> zsetRevRange(final String key, final long start,
									final long end) {
		Assert.hasText(key, "key 不能为空或null");
		return redisClient.exec(new ExecCaller<Set<String>>() {
			@Override
			public Set<String> exec(Jedis jedis) {
				Set<String> set = null;
				set = jedis.zrevrange(key, start, end);
				return set;
			}
		});
	}

	/**
	 * 返回名称为key的zset中的index从start到end的所有元素(从小到大排序)
	 *
	 * @param key
	 *            zset的key
	 * @param start
	 *            起始索引
	 * @param end
	 *            结束索引, -1表示返回全部元素
	 * @return
	 */
	public Set<String> zsetRange(final String key, final long start,
								 final long end) {
		Assert.hasText(key, "key 不能为空或null");
		return redisClient.exec(new ExecCaller<Set<String>>() {
			@Override
			public Set<String> exec(Jedis jedis) {
				Set<String> set = null;
				set = jedis.zrange(key, start, end);
				return set;
			}
		});
	}

	/**
	 * 返回名称为key的zset中元素member的score
	 *
	 * @param key
	 * @param member
	 * @return
	 */
	public Double zsetScore(final String key, final String member) {
		Assert.hasText(key, "key 不能为空或null");
		Assert.hasText(member, "member 不能为空或null");
		return redisClient.exec(new ExecCaller<Double>() {
			@Override
			public Double exec(Jedis jedis) {
				return jedis.zscore(key, member);
			}
		});
	}

	/**
	 * 为有序集 key 的成员 member 的score值加上增量 incr
	 *
	 * @param key
	 * @param incr
	 *            增量
	 * @param member
	 * @return
	 */
	public double zsetIncrBy(final String key, final double incr,
							 final String member) {
		Assert.hasText(key, "key 不能为空或null");
		Assert.hasText(member, "member 不能为空或null");
		return redisClient.exec(new ExecCaller<Double>() {
			@Override
			public Double exec(Jedis jedis) {
				return jedis.zincrby(key, incr, member);
			}
		});
	}

	/**
	 * 获得zset中member的rank值
	 * @param key
	 * @param member
	 * @return 返回排行
	 */
	public Long zsetRank(final String key, final String member){
		Assert.hasText(key, "key 不能为空或null");
		Assert.hasText(member, "member 不能为空或null");
		return redisClient.exec(new ExecCaller<Long>() {
			@Override
			public Long exec(Jedis jedis) {
				return jedis.zrank(key, member);
			}
		});
	}

	/**
	 * 删除有序集 key的成员 member
	 *
	 * @param key
	 * @param member成员
	 * @return 1删除 0未删除
	 */
	public long zsetRem(final String key,
						final String member) {
		Assert.hasText(key, "key 不能为空或null");
		Assert.hasText(member, "member 不能为空或null");
		return redisClient.exec(new ExecCaller<Long>() {
			@Override
			public Long exec(Jedis jedis) {
				return jedis.zrem(key, member);
			}
		});
	}

	/**
	 * 删除有序集 key的成员 members
	 *
	 * @param key
	 * @param members成员
	 * @return
	 */
	public long zsetRem(final String key,
			final String... members) {
		Assert.hasText(key, "key 不能为空或null");
		Assert.notEmpty(members, "members 不能为null，至少有一个元素");
		return redisClient.exec(new ExecCaller<Long>() {
			@Override
			public Long exec(Jedis jedis) {
				return jedis.zrem(key, members);
			}
		});
	}
	// ################## ZSet end ##################

	// ################## Object start ##################
	public <T> T getAndSet(String key, int expire,
						   TypeReference<T> typeReference, CacheCaller<T> caller) {
		return redisClient.getAndSet(key, expire, typeReference, caller);
	}

}
