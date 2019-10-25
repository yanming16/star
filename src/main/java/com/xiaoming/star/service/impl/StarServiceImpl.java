package com.xiaoming.star.service.impl;

import com.xiaoming.star.service.StarService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author liangyi
 * @Date 2019/10/25
 */
@Service
public class StarServiceImpl implements StarService {

    @Override
    @Async(value = "async1")
    public void starNum1(final String starName) {
        System.out.println(Thread.currentThread().getName());
}

    @Override
    @Async(value = "async1")
    public void starNum2(final String starName) {
        System.out.println(Thread.currentThread().getName());
    }

}
