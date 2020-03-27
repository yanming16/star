package com.xiaoming.star.service.impl;

import com.xiaoming.star.service.StarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author liangyi
 * @Date 2019/10/25
 */
@Service
public class StarServiceImpl implements StarService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StarServiceImpl.class);

    @Override
    @Async(value = "async1")
    public void starNum1(final String starName) throws InterruptedException {
        LOGGER.info(Thread.currentThread().getName() + "_start");
        Thread.sleep(10000);
        LOGGER.info(Thread.currentThread().getName() + "_end");
    }

    @Override
    @Async(value = "async2")
    public void starNum2(final String starName) throws InterruptedException {
        LOGGER.info(Thread.currentThread().getName() + "_start");
        Thread.sleep(10000);
        LOGGER.info(Thread.currentThread().getName() + "_end");
    }

}
