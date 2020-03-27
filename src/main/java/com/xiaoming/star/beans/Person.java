/*
 * Person.java
 * Copyright 2019 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

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
