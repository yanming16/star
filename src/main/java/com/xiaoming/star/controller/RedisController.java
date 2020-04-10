/*
 * RedisController.java
 * Copyright 2020 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package com.xiaoming.star.controller;

import com.xiaoming.star.redis.RedisLock;
import com.xiaoming.star.redis.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author liangyi
 * @Date 2020/3/27
 */
@RestController
public class RedisController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisController.class);

    public static final int VALUE = 50;

    @Autowired
    private RedisService mRedisService;

    @Autowired
    private RedisLock mRedisLock;

    @GetMapping("/redis/put")
    public String test(@RequestParam("name") String name, @RequestParam("desc") String desc) {
        return mRedisService.set(name, desc);
    }

    @GetMapping("/redis/lock")
    public String testLock(@RequestParam("key") String key) throws IOException {

        final String numName = key + "_num";
        mRedisService.set(numName, VALUE);

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 10l, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10000));

        for (int i = 0; i < 100; i++) {
            final String uuid = UUID.randomUUID().toString();
            threadPoolExecutor.submit(() -> {
                try {
                    if (mRedisLock.lock(key, uuid, 10)) {
                        final Integer currentNum = mRedisService.get(numName, Integer.class);
                        if (Objects.isNull(currentNum)) {
                            LOGGER.error("数量字段消失了！");
                            return;
                        }
                        LOGGER.info("名称：{}，数量：{}", key, currentNum);
                        if (currentNum <= 0) {
                            LOGGER.info("商品已经被抢完！");
                            return;
                        }
                        mRedisService.set(numName, currentNum-1);
                    }
                } catch (final Exception e) {
                    LOGGER.error("redis lock fail!");
                } finally {
                    if (!mRedisLock.unLock(key, uuid)) {
                        LOGGER.error("redis lock fail!");
                    }
                }
            });
        }

        System.in.read();
        return "success";
    }

    @GetMapping("/redis/test")
    public String setTest(@RequestParam("name") String name, @RequestParam("value") String value) {
        return mRedisLock.setTest(name, value);
    }



}
