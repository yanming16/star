package com.xiaoming.star.beans;

import lombok.Data;

import java.util.Map;

/**
 * @author liangyi
 * @Date 2019/11/20
 */
@Data
public class Person {

    private String name;

    private int age;

    private Map<String, String> cord;

}
