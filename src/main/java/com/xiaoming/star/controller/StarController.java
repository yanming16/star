package com.xiaoming.star.controller;

import com.xiaoming.star.service.StarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author liangyi
 * @Date 2019/10/25
 */
@RestController
public class StarController {

    @Autowired
    private StarService mStarService;

    @GetMapping("/test/get")
    public void test() {
        System.out.println("test1 start");
        mStarService.starNum1("test1");
        System.out.println("test2 start");
        mStarService.starNum2("test2");
        System.out.println("end");
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
}
