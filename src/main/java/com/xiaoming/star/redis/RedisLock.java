/*
 * RedisLock.java
 * Copyright 2020 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package com.xiaoming.star.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.SetParams;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author liangyi
 * @Date 2020/3/27
 */
@Component
public class RedisLock {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisLock.class);

    @Autowired
    private JedisPool jedisPool;

    private static final SetParams SET_PARAMS = new SetParams();

    static {
        SET_PARAMS.nx();
        SET_PARAMS.ex(1);
    }

    public String setTest(String lockName, String lockValue) {
        final Jedis jedis = jedisPool.getResource();
        return jedis.set(lockName, lockValue, SET_PARAMS);
    }

    /**
     * 阻塞式加锁
     * @param lockName
     * @param lockValue
     * @param expireTime
     * @return
     */
    public boolean lock(final String lockName, final String lockValue, final int expireTime) {

        final Jedis jedis = jedisPool.getResource();
        final SetParams setParams = new SetParams();
        setParams.nx();
        setParams.ex(expireTime);
        try {
            // 保证加锁和塞入超时时间为一个事务
            while (!"ok".equals(jedis.set(lockName, lockValue, setParams))){
                LOGGER.info("获取锁失败！");
                TimeUnit.SECONDS.sleep(1);
            }
            return true;
        } catch (final Exception e) {
            LOGGER.error("加锁异常！", e);
        } finally {
            LOGGER.info("关闭client！");
            jedis.close();
        }
        return false;
    }


    /**
     * 释放锁
     * @param key
     * @param lockValue
     * @return
     */
    public boolean unLock(final String key, final String lockValue) {
        final Jedis jedis = jedisPool.getResource();
        try {
            final String value = jedis.get(key);
            if (Objects.isNull(value)){
                LOGGER.info("锁已经被释放了！");
                return true;
            }
            if (!value.equals(lockValue)) {
                LOGGER.error("锁释放的uuid不正确");
                return false;
            }
            if (jedis.del(key) < 1) {
                LOGGER.error("锁释放失败");
                return false;
            }
            LOGGER.info("锁释放成功！");
        } catch (Exception e) {
            LOGGER.error("释放锁异常！", e);
            return false;
        } finally {
            LOGGER.info("关闭client！");
            jedis.close();
        }
        return true;
    }
}
