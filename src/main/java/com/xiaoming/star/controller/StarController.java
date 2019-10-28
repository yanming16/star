package com.xiaoming.star.controller;

import com.xiaoming.star.service.StarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author liangyi
 * @Date 2019/10/25
 */
@RestController
public class StarController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StarController.class);

    @Autowired
    private StarService mStarService;

    @GetMapping("/test/get")
    public String test() {
        System.out.println("test1 start");
        mStarService.starNum1("test1");
        System.out.println("test2 start");
        mStarService.starNum2("test2");
        System.out.println("end");
        return "ok";
    }

    @GetMapping("/test/get2")
    public String test2(@RequestParam(value = "num") final int num) {
        final String threadName = Thread.currentThread().getName();
        try {
            LOGGER.info("Thread: {} is start, num : {}", threadName, num);
            Thread.sleep(100);
        } catch (final InterruptedException e) {
            LOGGER.error("test2 interrupted exception! num : {}", num, e);
        }finally {
            LOGGER.info("Thread: {} is end, num : {}", threadName, num);
        }
        return threadName + " : ok";
    }

    @PostMapping("/test/post")
    public void testPost(@RequestBody final Map<String, String> map) {
        System.out.println(map.get("test"));
        System.out.println("test1 start");
        mStarService.starNum1("test1");
        System.out.println("test2 start");
        mStarService.starNum2("test2");
        System.out.println("end");
    }

    @PostMapping("/test/post2")
    public String testPost2(final int num) {
        final String threadName = Thread.currentThread().getName();
        try {
            LOGGER.info("Thread: {} is start, num : {}", threadName, num);
            Thread.sleep(100);
        } catch (final InterruptedException e) {
            LOGGER.error("test2 interrupted exception! num : {}", num, e);
        }finally {
            LOGGER.info("Thread: {} is end, num : {}", threadName, num);
        }
        return threadName + " : ok";
    }
}
