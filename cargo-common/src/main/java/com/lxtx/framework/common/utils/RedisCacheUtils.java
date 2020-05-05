/*
package com.lxtx.framework.common.utils;

import com.lxtx.framework.common.exception.LxtxException;
import com.lxtx.framework.common.utils.lock.RedisLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Tuple;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.TimeUnit;

*/
/**
 * <p>
 * redis 工具类
 * </p>
 *
 * @Author LiuLP on 2018/05/15.
 *//*

@Slf4j
public class RedisCacheUtils<T> {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private JedisCluster jedisCluster;


    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";
    private static final Long RELEASE_SUCCESS = 1L;

    */
/**
     * 两次锁请求之间的间隔时间，100毫秒
     *//*

    private static final int DEFAULT_ACQUIRY_RESOLUTION_MILLIS = 100;

    */
/**
     * 默认失效时间
     *//*

    public static final long LOCK_DEFAULT_EXPIRE_TIME = 30L * 60L * 1000L;
    */
/**
     * 默认失效时间为 一天
     *//*

    public static int DEFAULT_EXPIRE_TIME = 60 * 60 * 24;

    */
/**
     * key持久化（永不失效）
     * @param key
     * @return
     *//*

    public Boolean persist(Object key){
        return redisTemplate.persist(key);
    }

    */
/**
     * 缓存基本的对象，Integer、String、实体类等
     *
     * @param key   缓存的键值
     * @param value 缓存的值
     * @return 缓存的对象
     *//*

    public <T> ValueOperations<String, T> setCacheObject(String key, T value) {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        operation.set(key, value);
        return operation;
    }

    public Set<String> keys(String pattern) {
        log.debug("Start getting keys... ");
        TreeSet<String> keys = new TreeSet<>();
        Map<String, JedisPool> clusterNodes =jedisCluster.getClusterNodes();
        for (String key : clusterNodes.keySet()) {
            log.debug("Getting keys from: {}", key);
            JedisPool jedisPool = clusterNodes.get(key);
            Jedis jedisConn = jedisPool.getResource();
            try {
                keys.addAll(jedisConn.keys(pattern));
            } catch (Exception e) {
                log.error("Getting keys error: {}", e);
            } finally {
                log.debug("Jedis connection closed");
                jedisConn.close();
            }
        }
        log.debug("Keys gotten");
        return keys;
    }

    */
/**
     * 设置超时的缓存
     *
     * @param key
     * @param value
     * @param expire
     * @param <T>
     * @return
     *//*

    public <T> ValueOperations<String, T> setCacheObject(String key, T value, long expire) {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        operation.set(key, value, expire, TimeUnit.SECONDS);
        return operation;
    }
    public <T> ValueOperations<String, T> setCacheObject(String key, T value, long expire, TimeUnit unit) {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        operation.set(key, value, expire, unit);
        return operation;
    }


    */
/**
     * 删除缓存
     *
     * @param key
     *//*

    public void delete(String key) {
        redisTemplate.delete(key);
    }

    public void expire(String key, Integer time) {
        expire(key, time, TimeUnit.SECONDS);
    }

    public void expire(String key, Integer time, TimeUnit timeUnit) {
        redisTemplate.expire(key, time, timeUnit);
    }

    public Long increment(String key, Integer num) {
        Long increment = redisTemplate.opsForValue().increment(key, num);
        return increment;
    }


    */
/**
     * 获得缓存的基本对象。
     *
     * @param key 缓存键值
     * @return 缓存键值对应的数据
     *//*

    public <T> T getCacheObject(String key) {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        return operation.get(key);
    }
    */
/**
     * 获得缓存的基本对象。
     *
     * @param keys 缓存键值
     * @return 缓存键值对应的数据
     *//*

    public List<T>  mGetCacheObject(List<String> keys) {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        return operation.multiGet(keys);
    }

    */
/**
     * 缓存List数据
     *
     * @param key      缓存的键值
     * @param dataList 待缓存的List数据
     * @return 缓存的对象
     *//*

    public <T> ListOperations<String, T> setCacheList(String key, List<T> dataList) {
        ListOperations listOperation = redisTemplate.opsForList();
        if (null != dataList) {
            int size = dataList.size();
            for (int i = 0; i < size; i++) {
                listOperation.leftPush(key, dataList.get(i));
            }
        }
        return listOperation;
    }

    */
/**
     * 队列push
     *
     * @param key
     * @param entity
     * @return
     *//*

    public ListOperations<String, T> pushToQueue(String key, T entity) {
        ListOperations listOperation = redisTemplate.opsForList();
        listOperation.leftPush(key, entity);
        return listOperation;
    }

    */
/**
     * 不阻塞pop
     *
     * @param key
     * @return
     *//*

    public <T> T popFromQueue(String key) {
        ListOperations listOperation = redisTemplate.opsForList();
        return (T) listOperation.rightPop(key);
    }

    */
/**
     * 阻塞pop
     *
     * @param key
     * @return
     *//*

    public <T> T bRPopFromQueue(String key) {
        ListOperations listOperation = redisTemplate.opsForList();
        return (T) listOperation.rightPop(key, 0, TimeUnit.SECONDS);
    }

    */
/**
     * 缓存Set
     *
     * @param key     缓存键值
     * @param dataSet 缓存的数据
     * @return 缓存数据的对象
     *//*

    public <T> BoundSetOperations<String, T> setCacheSet(String key, Set<T> dataSet) {
        BoundSetOperations<String, T> setOperation = redisTemplate.boundSetOps(key);
        Iterator<T> it = dataSet.iterator();
        while (it.hasNext()) {
            setOperation.add(it.next());
        }
        return setOperation;
    }

    */
/**
     * 获得缓存的set
     *
     * @param key
     * @return
     *//*

    public Set<T> getCacheSet(String key) {
        Set<T> dataSet = new HashSet();
        BoundSetOperations<String, T> operation = redisTemplate.boundSetOps(key);
        Long size = operation.size();
        for (int i = 0; i < size; i++) {
            dataSet.add(operation.pop());
        }
        return dataSet;
    }

    */
/**
     * 缓存Map
     *
     * @param key
     * @param dataMap
     * @return
     *//*

    public <T> HashOperations<String, String, T> setCacheMap(String key, Map<String, T> dataMap) {
        HashOperations hashOperations = redisTemplate.opsForHash();
        if (null != dataMap) {
            for (Map.Entry<String, T> entry : dataMap.entrySet()) {
                hashOperations.put(key, entry.getKey(), entry.getValue());
            }
        }
        return hashOperations;
    }

    */
/**
     * 获得缓存的Map
     *
     * @param key
     * @return
     *//*

    public <T> Map<String, T> getCacheMap(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    public String hget(String key, String hasHkey) {
        Object value = redisTemplate.opsForHash().get(key, hasHkey);
        return (value == null) ? null : value.toString();
    }

    public void hset(String key, String hasHkey, String value) {
        redisTemplate.opsForHash().put(key, hasHkey, value);
    }

    public void hsetAll(String key, Map<String, Object> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    */
/**
     * 缓存Map
     *
     * @param key
     * @param dataMap
     * @return
     *//*

    public <T> HashOperations<String, Integer, T> setCacheIntegerMap(String key, Map<Integer, T> dataMap) {
        HashOperations hashOperations = redisTemplate.opsForHash();
        if (null != dataMap) {
            for (Map.Entry<Integer, T> entry : dataMap.entrySet()) {
                hashOperations.put(key, entry.getKey(), entry.getValue());
            }
        }
        return hashOperations;
    }

    */
/**
     * 获得缓存的Maph
     *
     * @param key
     * @return
     *//*

    public <T> Map<Integer, T> getCacheIntegerMap(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    */
/**
     * Redis消息发布
     *
     * @param channel
     * @param message
     *//*

    public void publish(String channel, Serializable message) {
        redisTemplate.convertAndSend(channel, message);
    }



    */
/**
     * 尝试获取分布式锁
     * @param lockKey 锁
     * @param requestId 请求标识
     * @param expireTime 超期时间 ，毫秒单位
     * @return 是否获取成功
     *//*

    @Deprecated
    public  boolean tryGetDistributedLock(String lockKey, String requestId, long expireTime) {

        String result = jedisCluster.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);

        if (LOCK_SUCCESS.equals(result)) {
            return true;
        }
        return false;

    }
    
    */
/**
     * 
     * @Description 获取锁的值
     * @param lockKey
     * @return
     *//*

    @Deprecated
    public String getDistributedLockRequestId(String lockKey) {
        String requestId = jedisCluster.get(lockKey);
        return requestId;
    }


    */
/**
     *  获取锁，有重试机制
     * @param lockKey
     * @param requestId  解锁人
     * @param expireTime 超时时间，毫秒单位
     * @param retryTime  重试时间，毫秒单位
     * @return
     *//*

    @Deprecated
    public  boolean tryGetDistributedLock(String lockKey, String requestId, long expireTime,long retryTime) {
        return tryGetDistributedLock(lockKey, requestId, expireTime, retryTime, DEFAULT_ACQUIRY_RESOLUTION_MILLIS);
    }

    */
/**
     *  获取锁，有重试机制
     * @param lockKey
     * @param requestId  解锁人
     * @param expireTime 超时时间，毫秒单位
     * @param retryTime  重试时间，毫秒单位
     * @param interval  重试间隔，毫秒单位
     * @return
     *//*

    @Deprecated
    public  boolean tryGetDistributedLock(String lockKey, String requestId, long expireTime, long retryTime, long interval) {
        //如果重试时间太短，不重试
        long timeout = retryTime - interval;
        if(timeout < 0){
            String result = jedisCluster.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
            if (LOCK_SUCCESS.equals(result)) {
                return true;
            }else{
                return false;
            }
        }
        while (timeout >= 0){
            String result = jedisCluster.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
            if (LOCK_SUCCESS.equals(result)) {
                return true;
            }else{
                try {
                    Thread.sleep(interval);
                } catch (InterruptedException e) {
                    log.error("休眠失败" + e);
                    throw LxtxException.newException("100001","重试失败");
                }
                timeout -= interval;
            }
        }

        return false;
    }


    */
/**
     * 释放分布式锁
     * @param lockKey 锁
     * @param requestId 请求标识
     * @return 是否释放成功
     *//*

    @Deprecated
    public  boolean releaseDistributedLock(String lockKey, String requestId) {

        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedisCluster.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));

        if (RELEASE_SUCCESS.equals(result)) {
            return true;
        }
        return false;
    }

    */
/**
     * 队列push
     *
     * @param key
     * @param entity
     * @return
     *//*

    public ListOperations<String, T> leftPushAll(String key, List<T> entity) {
        ListOperations listOperation = redisTemplate.opsForList();
        listOperation.leftPushAll(key, entity);
        return listOperation;
    }

    */
/**
     * 队列push
     *
     * @param key
     * @param entity
     * @return
     *//*

    public ListOperations<String, T> leftPush(String key, T entity) {
        ListOperations listOperation = redisTemplate.opsForList();
        listOperation.leftPush(key, entity);
        return listOperation;
    }

    */
/**
     * 返回list的长度
     * @param key
     * @return
     *//*

    public long llen(String key) {
        ListOperations listOperation = redisTemplate.opsForList();
        return listOperation.size(key);
    }

    */
/**
     * 判断key是否存在
     * @param key
     * @return
     *//*

    public boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }


    */
/**
     * 设置缓存
     *
     * @param key
     * @return
     *//*

    public Boolean zSetAdd(String key, Object value, String score) {
        return redisTemplate.opsForZSet().add(key, value, Double.parseDouble(score));
    }

    */
/**
     * 获取缓存
     *
     * @param key
     * @return
     *//*

    public Set zSetGetAll(String key) {
        return zSetRangeByScore(key, 0L, -1L);
    }


    */
/**
     * 获取缓存
     *
     * @param key
     * @return
     *//*

    public Set zSetRangeByScore(String key,long start,long end) {
        return redisTemplate.opsForZSet().range(key, start, end);
    }

    */
/**
     * 移除缓存
     *
     * @param key
     * @return
     *//*

    public Long zSetDel(String key,Object... objects) {
        return redisTemplate.opsForZSet().remove(key, objects);
    }
    */
/**
     * 移除缓存
     *
     * @param key
     * @return
     *//*

    public Long zSetDelAll(String key,long start,long end) {
        return redisTemplate.opsForZSet().removeRange(key, start, end);
    }
    
    public Set keys(Object pattern) {
    	return redisTemplate.keys(pattern);
    }

    */
/**
     * zount 统计数量
     * @param redisKey
     * @param startTime
     * @param endTime
     * @return
     *//*

    public Long zcount(String redisKey, double startTime, double endTime) {
        return redisTemplate.opsForZSet().count(redisKey, startTime, endTime);
    }

    public Long zcount(String redisKey) {
        return redisTemplate.opsForZSet().size(redisKey);
    }

    public Set<?> zrange(String redisKey, long start, long end) {
        return redisTemplate.opsForZSet().range(redisKey, start, end);
    }

    public Set<Tuple> zrangeByScoreWithScores(String redisKey, double startTime, double endTime) {
        return redisTemplate.opsForZSet().rangeByScoreWithScores(redisKey, startTime, endTime);
    }

    public void zadd(String redisKey, double timeStamp, Object s) {
        redisTemplate.opsForZSet().add(redisKey, s, timeStamp);
    }

    public void zremrangeByScore(String redisKey, double startTime, double endTime) {
        redisTemplate.opsForZSet().removeRangeByScore(redisKey, startTime, endTime);
    }

    public void setString(String key, String value, Long time, TimeUnit timeUnit) {
        ValueOperations<String, String> operation = redisTemplate.opsForValue();
        operation.set(key, value, time, timeUnit);
    }

    public void setInteger(String key, Integer value, Long time, TimeUnit timeUnit) {
        ValueOperations<String, String> operation = redisTemplate.opsForValue();
        operation.set(key, String.valueOf(value), time, timeUnit);
    }

    public String getString(String key) {
        ValueOperations<String, String> operation = redisTemplate.opsForValue();
        return operation.get(key);
    }

    public Integer getInteger(String key) {
        ValueOperations<String, String> operation = redisTemplate.opsForValue();
        String value = operation.get(key);
        return value == null ? 0 : Integer.parseInt(value);
    }

    public Long getLong(String key) {
        ValueOperations<String, String> operation = redisTemplate.opsForValue();
        String value = operation.get(key);
        return value == null ? 0L : Long.parseLong(value);
    }

    public RedisLock getLock(String key, long expire) {
        RedisLock redisLock = new RedisLock(jedisCluster, key, expire);
        return redisLock;
    }

    public RedisLock getLock(String key) {
        return getLock(key, 1 * 60 * 60 * 1000);
    }
}
*/
