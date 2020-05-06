package com.chaos.redis;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.stereotype.Component;
import redis.clients.jedis.BinaryClient.LIST_POSITION;
import redis.clients.jedis.*;

import java.util.*;

/**
 * @author chaos
 * @version
 */
@Component
public class JedisUtil {
	
	Log log = LogFactory.getLog(JedisUtil.class);
	
	//redis分布式锁属性 start
	private static final String LOCK_SUCCESS = "OK";
	private static final String SET_IF_NOT_EXIST = "NX";
	private static final String SET_WITH_EXPIRE_TIME = "PX";
	private static final Long RELEASE_SUCCESS = 1L;
	//redis分布式锁属性 end

	@Autowired
    private RedisProperties redisProperties;
	
	/**
	 * 缓存生存时间
	 */
	private int expire = 86400;
	private static JedisPool pool = null;
	
	private Jedis getJedis() {
		return getJedisSignle();
	}
	
	/**
	 * 从jedis连接池中获取获取jedis对象
	 * 
	 * @return
	 */
	private Jedis getJedisSignle() {

		if (pool == null) {
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxTotal(redisProperties.getPool().getMaxActive());
			config.setMaxIdle(redisProperties.getPool().getMaxIdle());
			config.setMinIdle(redisProperties.getPool().getMinIdle());

			pool = new JedisPool(config, redisProperties.getHost(), redisProperties.getPort(),180000,redisProperties.getPassword(),redisProperties.getDatabase());
		}

		Jedis jedis = null;
		try {
			jedis = pool.getResource();
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			if (jedis != null) {
				jedis.close();
				//pool.returnBrokenResource(jedisSingle);
			}
		}
		return jedis;
	}
	
	private void closeConn(Jedis jedis){
		// 正确释放资源
		if (jedis != null) {
			jedis.close();
			//pool.returnResource(jedisSingle);
		}
	}
	
	/**
	 * 获取JedisUtil实例
	 * 
	 * @return
	 */
	
	/*
	public static JedisUtil getInstance() {

		if (instance == null) {
			instance = new JedisUtil();
		}
		return instance;
	}
	*/

	/**
	 * 设置过期时间
	 * 
	 * @author ruan 2013-4-11
	 * @param key
	 * @param seconds
	 */
	public Long expire(String key, int seconds) {
		Long result = 0L;
		if (seconds <= 0) {
			return result;
		}
		Jedis jedis = null;
		try{
			jedis = getJedis();
			result = jedis.expire(key, seconds);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}finally{
			closeConn(jedis);
		}
		return result;
	}

	/**
	 * 设置默认过期时间
	 * 
	 * @author ruan 2013-4-11
	 * @param key
	 */
	public Long expire(String key) {
		return expire(key, expire);
	}

	// *******************************************Keys*******************************************//


	/**
	 * 设置key的过期时间,它是距历元（即格林威治标准时间 1970 年 1 月 1 日的 00:00:00，格里高利历）的偏移量。
	 * 
	 * @param String
	 *            key
	 * @param 时间
	 *            ,已秒为单位
	 * @return 影响的记录数
	 */
	public Long expireAt(String key, long timestamp) {
		Long count = 0L;
		Jedis jedis = null;
		try{
			jedis = getJedis();
			count = jedis.expireAt(key, timestamp);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}finally{
			closeConn(jedis);
		}
		return count;
	}

	/**
	 * 查询key的过期时间
	 * 
	 * @param String
	 *            key
	 * @return 以秒为单位的时间表示
	 */
	public Long ttl(String key) {

		Long len = 0L;
		Jedis jedis = null;
		try{
			jedis = getJedis();
			len = jedis.ttl(key);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}finally{
			closeConn(jedis);
		}
		return len;
	}

	/**
	 * 取消对key过期时间的设置
	 * 
	 * @param key
	 * @return 影响的记录数
	 */
	public long persist(String key) {
		
		Long count = 0L;
		Jedis jedis = null;
		try{
			jedis = getJedis();
			count = jedis.persist(key);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}finally{
			closeConn(jedis);
		}
		return count;
	}

	/**
	 * 删除key对应的记录
	 * 
	 * @param String
	 *            ... keys
	 * @return 删除的记录数
	 */
	public long del(String key) {
		
		Long count = 0L;
		Jedis jedis = null;
		try{
			jedis = getJedis();
			count = jedis.del(key);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}finally{
			closeConn(jedis);
		}
		return count;
	}

	/**
	 * 判断key是否存在
	 * 
	 * @param String
	 *            key
	 * @return boolean
	 */
	public boolean exists(String key) {
		
		boolean exis = false;
		Jedis jedis = null;
		try{
			jedis = getJedis();
			exis = jedis.exists(key);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}finally{
			closeConn(jedis);
		}
		return exis;
	}

	/**
	 * 对List,Set,SortSet进行排序,如果集合数据较大应避免使用这个方法
	 * 
	 * @param String
	 *            key
	 * @return List<String> 集合的全部记录
	 **/
	public List<String> sort(String key) {
		List<String> list = null;
		Jedis jedis = null;
		try{
			jedis = getJedis();
			list = jedis.sort(key);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}finally{
			closeConn(jedis);
		}
		return list;
	}

	/**
	 * 对List,Set,SortSet进行排序或limit
	 * 
	 * @param String
	 *            key
	 * @param SortingParams
	 *            parame 定义排序类型或limit的起止位置.
	 * @return List<String> 全部或部分记录
	 **/
	public List<String> sort(String key, SortingParams parame) {
		List<String> list = null;
		Jedis jedis = null;
		try{
			jedis = getJedis();
			list = jedis.sort(key, parame);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}finally{
			closeConn(jedis);
		}
		return list;
	}

	/**
	 * 返回指定key存储的类型
	 * 
	 * @param String
	 *            key
	 * @return String string|list|set|zset|hash
	 **/
	public String type(String key) {
		String type= null;
		Jedis jedis = null;
		try{
			jedis = getJedis();
			type = jedis.type(key);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}finally{
			closeConn(jedis);
		}
		return type;
	}

	// *******************************************Sets*******************************************//

	/**
	 * 向Set添加一条记录，如果member已存在返回0,否则返回1
	 * 
	 * @param String
	 *            key
	 * @param String
	 *            member
	 * @return 操作码,0或1
	 */
	public Long sadd(String key, String member) {
		Long s = 0L;
		Jedis jedis = null;
		try{
			jedis = getJedis();
			s = jedis.sadd(key, member);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}finally{
			closeConn(jedis);
		}
		return s;
	}

	public Long sadd(String key, List<String> members) {
		Long s = 0L;
		Jedis jedis = null;
		try {
			jedis = getJedis();
			s = jedis.sadd(key, members.toArray(new String[0]));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			closeConn(jedis);
		}
		return s;
	}
	
	/**
	 * 获取给定key中元素个数
	 * 
	 * @param String
	 *            key
	 * @return 元素个数
	 */
	public long scard(String key) {
		Long len = 0L;
		Jedis jedis = null;
		try{
			jedis = getJedis();
			len = jedis.scard(key);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}finally{
			closeConn(jedis);
		}
		return len;
	}

	/**
	 * 确定一个给定的值是否存在
	 * 
	 * @param String
	 *            key
	 * @param String
	 *            member 要判断的值
	 * @return 存在返回1，不存在返回0
	 **/
	public boolean sismember(String key, String member) {
		boolean exist = false;
		Jedis jedis = null;
		try{
			jedis = getJedis();
			exist = jedis.sismember(key, member);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}finally{
			closeConn(jedis);
		}
		return exist;
	}

	/**
	 * 返回集合中的所有成员
	 * 
	 * @param String
	 *            key
	 * @return 成员集合
	 */
	public Set<String> smembers(String key) {
		Set<String> set = null;
		Jedis jedis = null;
		try{
			jedis = getJedis();
			set = jedis.smembers(key);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}finally{
			closeConn(jedis);
		}
		return set;
	}

	/**
	 * 从集合中删除成员
	 * 
	 * @param String
	 *            key
	 * @return 被删除的成员
	 */
	public String spop(String key) {
		String value = null;
		Jedis jedis = null;
		try{
			jedis = getJedis();
			value = jedis.spop(key);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}finally{
			closeConn(jedis);
		}
		return value;
	}

	/**
	 * 从集合中删除指定成员
	 * 
	 * @param String
	 *            key
	 * @param String
	 *            member 要删除的成员
	 * @return 状态码，成功返回1，成员不存在返回0
	 */
	public Long srem(String key, String member) {
		Long result = 0L;
		Jedis jedis = null;
		try{
			jedis = getJedis();
			result = jedis.srem(key, member);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}finally{
			closeConn(jedis);
		}
		return result;
	}
	
	public Long srem(String key, List<String> members) {
	    Long s = 0L;
	    Jedis jedis = null;
	    try {
	        jedis = getJedis();
	        s = jedis.srem(key, members.toArray(new String[0]));
        } catch (Exception e) {
	        log.error(e.getMessage(), e);
        } finally {
	        closeConn(jedis);
        }
        return s;
    }

	// *******************************************SortSet*******************************************//

	/**
	 * 向集合中增加一条记录,如果这个值已存在，这个值对应的权重将被置为新的权重
	 * 
	 * @param String
	 *            key
	 * @param String
	 *            member 要加入的值，
	 * @param double
	 *            score 权重
	 * @return 状态码 1成功，0已存在member的值
	 */
	public Long zadd(String key, String member, double score) {
		Long result = 0L;
		Jedis jedis = null;
		try{
			jedis = getJedis();
			result = jedis.zadd(key, score, member);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}finally{
			closeConn(jedis);
		}
		return result;
	}

	public Long zadd(String key, Map<String, Double> scoreMembers) {
		Long result = 0L;
		Jedis jedis = null;
		try{
			jedis = getJedis();
			result = jedis.zadd(key, scoreMembers);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}finally{
			closeConn(jedis);
		}
		return result;
	}

	/**
	 * 获取集合中元素的数量
	 * 
	 * @param String
	 *            key
	 * @return 如果返回0则集合不存在
	 */
	public Long zcard(String key) {
		Long len = 0L;
		Jedis jedis = null;
		try{
			jedis = getJedis();
			len = jedis.zcard(key);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}finally{
			closeConn(jedis);
		}
		return len;
	}

	/**
	 * 获取指定权重区间内集合的数量
	 * 
	 * @param String
	 *            key
	 * @param double
	 *            min 最小排序位置
	 * @param double
	 *            max 最大排序位置
	 */
	public Long zcount(String key, double min, double max) {
		Long len = 0L;
		Jedis jedis = null;
		try{
			jedis = getJedis();
			len = jedis.zcount(key, min, max);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}finally{
			closeConn(jedis);
		}
		return len;
	}

	/**
	 * 获得set的长度
	 * 
	 * @param key
	 * @return
	 */
	public int zlength(String key) {
		int len = 0;
		Jedis jedis = null;
		try{
			jedis = getJedis();
			Set<String> set = zrange(key, 0, -1);
			len = set.size();
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}finally{
			closeConn(jedis);
		}
		return len;
	}

	/**
	 * 权重增加给定值，如果给定的member已存在
	 * 
	 * @param String
	 *            key
	 * @param double
	 *            score 要增的权重
	 * @param String
	 *            member 要插入的值
	 * @return 增后的权重
	 */
	public double zincrby(String key, double score, String member) {
		Double s = 0d;
		Jedis jedis = null;
		try{
			jedis = getJedis();
			s = jedis.zincrby(key, score, member);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}finally{
			closeConn(jedis);
		}
		return s;
	}

	/**
	 * 返回指定位置的集合元素,0为第一个元素，-1为最后一个元素
	 * 
	 * @param String
	 *            key
	 * @param int
	 *            start 开始位置(包含)
	 * @param int
	 *            end 结束位置(包含)
	 * @return Set<String>
	 */
	public Set<String> zrange(String key, int start, int end) {
		Set<String> set  = null;
		Jedis jedis = null;
		try{
			jedis = getJedis();
			set = jedis.zrange(key, start, end);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}finally{
			closeConn(jedis);
		}
		return set;
	}

	/**
	 * 返回指定权重区间的元素集合
	 * 
	 * @param String
	 *            key
	 * @param double
	 *            min 上限权重
	 * @param double
	 *            max 下限权重
	 * @return Set<String>
	 */
	public Set<String> zrangeByScore(String key, double min, double max) {
		Set<String> set  = null;
		Jedis jedis = null;
		try{
			jedis = getJedis();
			set = jedis.zrangeByScore(key, min, max);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}finally{
			closeConn(jedis);
		}
		return set;
	}

	/**
	 * 获取指定值在集合中的位置，集合排序从低到高
	 * 
	 * @see zrevrank
	 * @param String
	 *            key
	 * @param String
	 *            member
	 * @return long 位置
	 */
	public Long zrank(String key, String member) {
		Long index  = 0L;
		Jedis jedis = null;
		try{
			jedis = getJedis();
			index = jedis.zrank(key, member);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}finally{
			closeConn(jedis);
		}
		return index;
	}

	/**
	 * 获取指定值在集合中的位置，集合排序从高到低
	 * 
	 * @see zrank
	 * @param String
	 *            key
	 * @param String
	 *            member
	 * @return long 位置
	 */
	public Long zrevrank(String key, String member) {
		Long index  = 0L;
		Jedis jedis = null;
		try{
			jedis = getJedis();
			index = jedis.zrevrank(key, member);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}finally{
			closeConn(jedis);
		}
		return index;
	}

	/**
	 * 从集合中删除成员
	 * 
	 * @param String
	 *            key
	 * @param String
	 *            member
	 * @return 返回1成功
	 */
	public long zrem(String key, String member) {
		Long result  = 0L;
		Jedis jedis = null;
		try{
			jedis = getJedis();
			result = jedis.zrem(key, member);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}finally{
			closeConn(jedis);
		}
		return result;
	}

	/**
	 * 删除
	 * 
	 * @param key
	 * @return
	 */
	public Long zrem(String key) {
		Long result  = 0L;
		Jedis jedis = null;
		try{
			jedis = getJedis();
			result = jedis.del(key);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}finally{
			closeConn(jedis);
		}
		return result;
	}

	/**
	 * 删除给定位置区间的元素
	 * 
	 * @param String
	 *            key
	 * @param int
	 *            start 开始区间，从0开始(包含)
	 * @param int
	 *            end 结束区间,-1为最后一个元素(包含)
	 * @return 删除的数量
	 */
	public Long zremrangeByRank(String key, int start, int end) {
		Long result  = 0L;
		Jedis jedis = null;
		try{
			jedis = getJedis();
			result = jedis.zremrangeByRank(key, start, end);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}finally{
			closeConn(jedis);
		}
		return result;
	}

	/**
	 * 删除给定权重区间的元素
	 * 
	 * @param String
	 *            key
	 * @param double
	 *            min 下限权重(包含)
	 * @param double
	 *            max 上限权重(包含)
	 * @return 删除的数量
	 */
	public Long zremrangeByScore(String key, double min, double max) {
		Long result  = 0L;
		Jedis jedis = null;
		try{
			jedis = getJedis();
			result = jedis.zremrangeByScore(key, min, max);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}finally{
			closeConn(jedis);
		}
		return result;
	}

	/**
	 * 获取给定区间的元素，原始按照权重由高到低排序
	 * 
	 * @param String
	 *            key
	 * @param int
	 *            start
	 * @param int
	 *            end
	 * @return Set<String>
	 */
	public Set<String> zrevrange(String key, int start, int end) {
		Set<String> set  = null;
		Jedis jedis = null;
		try{
			jedis = getJedis();
			set = jedis.zrevrange(key, start, end);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}finally{
			closeConn(jedis);
		}
		return set;
	}

	/**
	 * 获取给定值在集合中的权重
	 * 
	 * @param String
	 *            key
	 * @param memeber
	 * @return double 权重
	 */
	public double zscore(String key, String memebr) {
		Double score  = 0d;
		Jedis jedis = null;
		try{
			jedis = getJedis();
			score = jedis.zscore(key, memebr);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}finally{
			closeConn(jedis);
		}
		return score;
	}

	// *******************************************Hash*******************************************//

	/**
	 * 从hash中删除指定的存储
	 * 
	 * @param String
	 *            key
	 * @param String
	 *            fieid 存储的名字
	 * @return 状态码，1成功，0失败
	 */
	public Long hdel(String key, String fieid) {
		Long result  = 0L;
		Jedis jedis = null;
		try{
			jedis = getJedis();
			result = jedis.hdel(key, fieid);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}finally{
			closeConn(jedis);
		}
		return result;
	}

	public Long hdel(String key) {
		Long result  = 0L;
		Jedis jedis = null;
		try{
			jedis = getJedis();
			result = jedis.del(key);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}finally{
			closeConn(jedis);
		}
		return result;
	}

	/**
	 * 测试hash中指定的存储是否存在
	 * 
	 * @param String
	 *            key
	 * @param String
	 *            fieid 存储的名字
	 * @return 1存在，0不存在
	 */
	public boolean hexists(String key, String fieid) {
		boolean result  = false;
		Jedis jedis = null;
		try{
			jedis = getJedis();
			result = jedis.hexists(key, fieid);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}finally{
			closeConn(jedis);
		}
		return result;
	}

	/**
	 * 返回hash中指定存储位置的值
	 * 
	 * @param String
	 *            key
	 * @param String
	 *            fieid 存储的名字
	 * @return 存储对应的值
	 */
	public String hget(String key, String fieid) {
		String result  = null;
		Jedis jedis = null;
		try{
			jedis = getJedis();
			result = jedis.hget(key, fieid);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}finally{
			closeConn(jedis);
		}
		return result;
	}

	/**
	 * 以Map的形式返回hash中的存储和值
	 * 
	 * @param String
	 *            key
	 * @return Map<Strinig,String>
	 */
	public Map<String, String> hgetAll(String key) {
		Map<String,String> result = null;
		Jedis jedis = null;
		try{
			jedis = getJedis();
			result = jedis.hgetAll(key);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}finally{
			closeConn(jedis);
		}
		return result;
	}

	/**
	 * 添加一个对应关系
	 * 
	 * @param String
	 *            key
	 * @param String
	 *            fieid
	 * @param String
	 *            value
	 * @return 状态码 1成功，0失败，fieid已存在将更新，也返回0
	 **/
	public Long hset(String key, String fieid, String value) {
		Long result = 0L;
		Jedis jedis = null;
		try{
			jedis = getJedis();
			result = jedis.hset(key, fieid, value);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}finally{
			closeConn(jedis);
		}
		return result;
	}

	/**
	 * 添加对应关系，只有在fieid不存在时才执行
	 * 
	 * @param String
	 *            key
	 * @param String
	 *            fieid
	 * @param String
	 *            value
	 * @return 状态码 1成功，0失败fieid已存
	 **/
	public Long hsetnx(String key, String fieid, String value) {
		Long result = 0L;
		Jedis jedis = null;
		try{
			jedis = getJedis();
			result = jedis.hsetnx(key, fieid, value);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}finally{
			closeConn(jedis);
		}
		return result;
	}

	/**
	 * 获取hash中value的集合
	 * 
	 * @param String
	 *            key
	 * @return List<String>
	 */
	public List<String> hvals(String key) {
		List<String> list = null;
		Jedis jedis = null;
		try{
			jedis = getJedis();
			list = jedis.hvals(key);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}finally{
			closeConn(jedis);
		}
		return list;
	}

	/**
	 * 在指定的存储位置加上指定的数字，存储位置的值必须可转为数字类型
	 * 
	 * @param String
	 *            key
	 * @param String
	 *            fieid 存储位置
	 * @param String
	 *            long value 要增加的值,可以是负数
	 * @return 增加指定数字后，存储位置的值
	 */
	public Long hincrby(String key, String fieid, long value) {
		Long index = 0L;
		Jedis jedis = null;
		try{
			jedis = getJedis();
			index = jedis.hincrBy(key, fieid, value);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}finally{
			closeConn(jedis);
		}
		return index;
	}

	/**
	 * 返回指定hash中的所有存储名字,类似Map中的keySet方法
	 * 
	 * @param String
	 *            key
	 * @return Set<String> 存储名称的集合
	 */
	public Set<String> hkeys(String key) {
		Set<String> set = null;
		Jedis jedis = null;
		try{
			jedis = getJedis();
			set = jedis.hkeys(key);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}finally{
			closeConn(jedis);
		}
		return set;
	}

	/**
	 * 获取hash中存储的个数，类似Map中size方法
	 * 
	 * @param String
	 *            key
	 * @return long 存储的个数
	 */
	public Long hlen(String key) {
		Long len = 0L;
		Jedis jedis = null;
		try{
			jedis = getJedis();
			len = jedis.hlen(key);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}finally{
			closeConn(jedis);
		}
		return len;
	}

	/**
	 * 根据多个key，获取对应的value，返回List,如果指定的key不存在,List对应位置为null
	 * 
	 * @param String
	 *            key
	 * @param String
	 *            ... fieids 存储位置
	 * @return List<String>
	 */
	public List<String> hmget(String key, String... fieids) {
		List<String> list = null;
		Jedis jedis = null;
		try{
			jedis = getJedis();
			list = jedis.hmget(key, fieids);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}finally{
			closeConn(jedis);
		}
		return list;
	}

	/**
	 * 添加对应关系，如果对应关系已存在，则覆盖
	 * 
	 * @param Strin
	 *            key
	 * @param Map
	 *            <String,String> 对应关系
	 * @return 状态，成功返回OK
	 */
	public String hmset(String key, Map<String, String> map) {
		String result = null;
		Jedis jedis = null;
		try{
			jedis = getJedis();
			result = jedis.hmset(key, map);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}finally{
			closeConn(jedis);
		}
		return result;
	}

	// *******************************************Strings*******************************************//
	/**
	 * 根据key获取记录
	 * 
	 * @param String
	 *            key
	 * @return 值
	 */
	public String get(String key) {
		String value = null;
		Jedis jedis = null;
		try{
			jedis = getJedis();
			value = jedis.get(key);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}finally{
			closeConn(jedis);
		}
		return value;
	}

	/**
	 * 添加有过期时间的记录
	 * 
	 * @param String
	 *            key
	 * @param int
	 *            seconds 过期时间，以秒为单位
	 * @param String
	 *            value
	 * @return String 操作状态
	 */
	public String setEx(String key, int seconds, String value) {
		String result = null;
		Jedis jedis = null;
		try{
			jedis = getJedis();
			result = jedis.setex(key, seconds, value);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}finally{
			closeConn(jedis);
		}
		return result;
	}

	/**
	 * 添加一条记录，仅当给定的key不存在时才插入
	 * 
	 * @param String
	 *            key
	 * @param String
	 *            value
	 * @return long 状态码，1插入成功且key不存在，0未插入，key存在
	 */
	public Long setnx(String key, String value) {
		Long result = null;
		Jedis jedis = null;
		try{
			jedis = getJedis();
			result = jedis.setnx(key, value);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}finally{
			closeConn(jedis);
		}
		return result;
	}

	/**
	 * 添加记录,如果记录已存在将覆盖原有的value
	 * 
	 * @param String
	 *            key
	 * @param String
	 *            value
	 * @return 状态码
	 */
	public String set(String key, String value) {
		String result = null;
		Jedis jedis = null;
		try{
			jedis = getJedis();
			result = jedis.set(key, value);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}finally{
			closeConn(jedis);
		}
		return result;
	}

	/**
	 * 从指定位置开始插入数据，插入的数据会覆盖指定位置以后的数据<br/>
	 * 例:String str1="123456789";<br/>
	 * 对str1操作后setRange(key,4,0000)，str1="123400009";
	 * 
	 * @param String
	 *            key
	 * @param long
	 *            offset
	 * @param String
	 *            value
	 * @return long value的长度
	 */
	public Long setRange(String key, long offset, String value) {
		Long len = 0L;
		Jedis jedis = null;
		try{
			jedis = getJedis();
			len = jedis.setrange(key, offset, value);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}finally{
			closeConn(jedis);
		}
		return len;
	}

	/**
	 * 在指定的key中追加value
	 * 
	 * @param String
	 *            key
	 * @param String
	 *            value
	 * @return long 追加后value的长度
	 **/
	public Long append(String key, String value) {
		Long len = 0L;
		Jedis jedis = null;
		try{
			jedis = getJedis();
			len = jedis.append(key, value);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}finally{
			closeConn(jedis);
		}
		return len;
	}

	/**
	 * 将key对应的value减去指定的值，只有value可以转为数字时该方法才可用
	 * 
	 * @param String
	 *            key
	 * @param long
	 *            number 要减去的值
	 * @return long 减指定值后的值
	 */
	public Long decrBy(String key, long number) {
		Long len = 0L;
		Jedis jedis = null;
		try{
			jedis = getJedis();
			len = jedis.decrBy(key, number);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}finally{
			closeConn(jedis);
		}
		return len;
	}

	/**
	 * <b>可以作为获取唯一id的方法</b><br/>
	 * 将key对应的value加上指定的值，只有value可以转为数字时该方法才可用
	 * 
	 * @param String
	 *            key
	 * @param long
	 *            number 要相加的值
	 * @return long 相加后的值
	 */
	public Long incrBy(String key, long number) {
		Long result = 0L;
		Jedis jedis = null;
		try{
			jedis = getJedis();
			result = jedis.incrBy(key, number);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}finally{
			closeConn(jedis);
		}
		return result;
	}

	/**
	 * 对指定key对应的value进行截取
	 * 
	 * @param String
	 *            key
	 * @param long
	 *            startOffset 开始位置(包含)
	 * @param long
	 *            endOffset 结束位置(包含)
	 * @return String 截取的值
	 */
	public String getrange(String key, long startOffset, long endOffset) {
		String value = null;
		Jedis jedis = null;
		try{
			jedis = getJedis();
			value = jedis.getrange(key, startOffset, endOffset);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}finally{
			closeConn(jedis);
		}
		return value;
	}

	/**
	 * 获取并设置指定key对应的value<br/>
	 * 如果key存在返回之前的value,否则返回null
	 * 
	 * @param String
	 *            key
	 * @param String
	 *            value
	 * @return String 原始value或null
	 */
	public String getSet(String key, String value) {
		String result = null;
		Jedis jedis = null;
		try{
			jedis = getJedis();
			result = jedis.getSet(key, value);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}finally{
			closeConn(jedis);
		}
		return result;
	}

	/**
	 * 获取key对应的值的长度
	 * 
	 * @param String
	 *            key
	 * @return value值得长度
	 */
	public Long strlen(String key) {
		Long len = 0L;
		Jedis jedis = null;
		try{
			jedis = getJedis();
			len = jedis.strlen(key);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}finally{
			closeConn(jedis);
		}
		return len;
	}

	// *******************************************Lists*******************************************//

	/**
	 * List长度
	 * 
	 * @param String
	 *            key
	 * @return 长度
	 */
	public Long llen(String key) {
		Long count = 0L;
		Jedis jedis = null;
		try{
			jedis = getJedis();
			count = jedis.llen(key);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}finally{
			closeConn(jedis);
		}
		return count;
	}

	/**
	 * 覆盖操作,将覆盖List中指定位置的值
	 * 
	 * @param key
	 * @param int
	 *            index 位置
	 * @param String
	 *            value 值
	 * @return 状态码
	 */
	public String lset(String key, int index, String value) {
		String status = null;
		Jedis jedis = null;
		try{
			jedis = getJedis();
			status = jedis.lset(key, index, value);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}finally{
			closeConn(jedis);
		}
		return status;
	}

	/**
	 * 在value的相对位置插入记录
	 * 
	 * @param key
	 * @param LIST_POSITION
	 *            前面插入或后面插入
	 * @param String
	 *            pivot 相对位置的内容
	 * @param String
	 *            value 插入的内容
	 * @return 记录总数
	 */
	public Long linsert(String key, LIST_POSITION where, String pivot, String value) {
		Long size = 0L;
		Jedis jedis = null;
		try{
			jedis = getJedis();
			size = jedis.linsert(key, where, pivot, value);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}finally{
			closeConn(jedis);
		}
		return size;
	}

	/**
	 * 获取List中指定位置的值
	 * 
	 * @param String
	 *            key
	 * @param int
	 *            index 位置
	 * @return 值
	 **/
	public String lindex(String key, int index) {
		String value = null;
		Jedis jedis = null;
		try{
			jedis = getJedis();
			value = jedis.lindex(key, index);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}finally{
			closeConn(jedis);
		}
		return value;
	}

	/**
	 * 将List中的第一条记录移出List
	 * 
	 * @param String
	 *            key
	 * @return 移出的记录
	 */
	public String lpop(String key) {
		String value = null;
		Jedis jedis = null;
		try{
			jedis = getJedis();
			value = jedis.lpop(key);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}finally{
			closeConn(jedis);
		}
		return value;
	}

	/**
	 * 将List中最后第一条记录移出List
	 * 
	 * @param byte[]
	 *            key
	 * @return 移出的记录
	 */
	public String rpop(String key) {
		String value = null;
		Jedis jedis = null;
		try{
			jedis = getJedis();
			value = jedis.rpop(key);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}finally{
			closeConn(jedis);
		}
		return value;
	}

	/**
	 * 向List尾部追加记录
	 * 
	 * @param String
	 *            key
	 * @param String
	 *            value
	 * @return 记录总数
	 */
	public Long lpush(String key, String value) {
		Long count = 0L;
		Jedis jedis = null;
		try{
			jedis = getJedis();
			count = jedis.lpush(key, value);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}finally{
			closeConn(jedis);
		}
		return count;
	}

	/**
	 * 向List头部追加记录
	 * 
	 * @param String
	 *            key
	 * @param String
	 *            value
	 * @return 记录总数
	 */
	public Long rpush(String key, String value) {
		Long count = 0L;
		Jedis jedis = null;
		try{
			jedis = getJedis();
			count = jedis.rpush(key, value);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}finally{
			closeConn(jedis);
		}
		return count;
	}

	/**
	 * 获取指定范围的记录，可以做为分页使用
	 * 
	 * @param String
	 *            key
	 * @param long
	 *            start
	 * @param long
	 *            end
	 * @return List
	 */
	public List<String> lrange(String key, long start, long end) {
		List<String> list = null;
		Jedis jedis = null;
		try{
			jedis = getJedis();
			list = jedis.lrange(key, start, end);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}finally{
			closeConn(jedis);
		}
		return list;
	}

	/**
	 * 删除List中c条记录，被删除的记录值为value
	 * 
	 * @param String
	 *            key
	 * @param int
	 *            c 要删除的数量，如果为负数则从List的尾部检查并删除符合的记录
	 * @param String
	 *            value 要匹配的值
	 * @return 删除后的List中的记录数
	 */
	public Long lrem(String key, int c, String value) {
		Long count = 0L;
		Jedis jedis = null;
		try{
			jedis = getJedis();
			count = jedis.lrem(key, c, value);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}finally{
			closeConn(jedis);
		}
		return count;
	}

	/**
	 * 算是删除吧，只保留start与end之间的记录
	 * 
	 * @param String
	 *            key
	 * @param int
	 *            start 记录的开始位置(0表示第一条记录)
	 * @param int
	 *            end 记录的结束位置（如果为-1则表示最后一个，-2，-3以此类推）
	 * @return 执行状态码
	 */
	public String ltrim(String key, int start, int end) {
		String status = null;
		Jedis jedis = null;
		try{
			jedis = getJedis();
			status = jedis.ltrim(key, start, end);
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}finally{
			closeConn(jedis);
		}
		return status;
	}

    /**
     * 遍历redis所有key
     * 
     * @param key key+*
     * @return 结果
     */
	public List<String> scan(String key) {
        List<String> list = new ArrayList<>();
        Jedis jedis = null;
        try{
            ScanParams params = new ScanParams();
            params.match(key + "*");
            params.count(10);
            jedis = getJedis();
            ScanResult<String> result = jedis.scan("0", params);
            list.addAll(result.getResult());
        }catch(Exception e){
            log.error(e.getMessage(),e);
        }finally{
            closeConn(jedis);
        }
        return list;
    }
	
//	public static void main(String[] args){
////		JedisUtil jedis = new JedisUtil();
////		jedis.set("test003", "test003");
////		System.out.println(jedis.get("test003"));
//	}
	
	/**参考文档 https://mp.weixin.qq.com/s?__biz=MjM5NzMyMjAwMA==&mid=2651479543&idx=1&sn=4ec576a71901d9207a4ddec0eb3e7c32&chksm=bd2531888a52b89ec7b0d4efe04672397f1840ba1e948b6622acca753d8901ef824c5f2a0c0a&mpshare=1&scene=1&srcid=1213XuAplWTCz4j8M7Qu8t4K&key=703ab09a9dfbc0dca08d86147aee92e4c33c46a62a31de34142833d259ad41ef795ccdca589d0d94b26c875915266f7664fc5c7e060db802dc0186cf17334946d79238675e24147266d78455b79eb577&ascene=0&uin=MTUyNzc3NDYyMA%3D%3D&devicetype=iMac+MacBookPro12%2C1+OSX+OSX+10.11.6+build(15G1421)&version=12020810&nettype=WIFI&fontScale=100&pass_ticket=I00mcl9Rn8NHK0W6zGbFk9nWR8egFU94CYv%2BAlWMSMNCgXzcTNdd0kOu86IKd8NS*/
	 /**
    * 尝试获取分布式锁
    * @param lockKey 锁
    * @param requestId 请求标识
    * @param expireTime 超期时间 毫秒
    * @return 是否获取成功,如果获取失败则不能执行锁内逻辑，进行操作失败提示
    */
   public boolean tryGetDistributedLock(String lockKey, String requestId, int expireTime) {
		Jedis jedis = null;
		try{
			jedis = getJedis();
			String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
	        if (LOCK_SUCCESS.equals(result)) {
	            return true;
	        }
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}finally{
			closeConn(jedis);
		}
		return false;
   }
   
   /**
    * 释放分布式锁
    * @param lockKey 锁
    * @param requestId 请求标识
    * @return 是否释放成功
    */
   public boolean releaseDistributedLock(String lockKey, String requestId) {
   	Jedis jedis = null;
		try{
			jedis = getJedis();
	        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
	        Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));
	        if (RELEASE_SUCCESS.equals(result)) {
	            return true;
	        }
		}catch(Exception e){
			log.error(e.getMessage(),e);
		}finally{
			closeConn(jedis);
		}
		return false;
   }
}