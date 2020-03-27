/*
 * BeanUtilsTest.java
 * Copyright 2019 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package com.xiaoming.star;

import com.xiaoming.star.beans.Person;
import com.xiaoming.star.utils.JacksonUtil;
import org.assertj.core.util.Maps;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

/**
 * @author liangyi
 * @Date 2019/11/20
 */
public class BeanUtilsTest {

    @Test
    public void deepCopy() {
        Person person = new Person();
        person.setName("test");
        person.setAge(18);
        final Map<String, String> cord = Maps.newHashMap("职业", "教师");
        person.setCord(cord);
        System.out.println(JacksonUtil.encode2String(person));
        Person newPerson = new Person();
        BeanUtils.copyProperties(person, newPerson);
        //newPerson.getCord().put("身份", "杀手");
        System.out.println(JacksonUtil.encode2String(person));
        System.out.println(JacksonUtil.encode2String(newPerson));
        final String personStr = JacksonUtil.encode2String(person);
        Person newPerson2 = JacksonUtil.decodeFromString(personStr, Person.class);
        System.out.println(JacksonUtil.encode2String(newPerson2));
        newPerson2.getCord().put("身份", "杀手");
        System.out.println(JacksonUtil.encode2String(person));
        System.out.println(JacksonUtil.encode2String(newPerson2));
    }
}
