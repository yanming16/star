/*
 * FloatTest.java
 * Copyright 2020 Qunhe Tech, all rights reserved.
 * Qunhe PROPRIETARY/CONFIDENTIAL, any form of usage is subject to approval.
 */

package com.xiaoming.star;

import com.xiaoming.star.utils.JacksonUtil;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

/**
 * @author liangyi
 * @Date 2020/4/7
 */
public class FloatTest {

    @Test
    public void test1() {
        float a = 1.0f;
        float b = 1.0f;
        System.out.println(a == b);
        float c = a;
        System.out.println(c);
    }

    @Test
    public void test2() {
        Float a = new Float(1.0);
        Float b = new Float(1.0);
        System.out.println(a.equals(b));
        System.out.println(a.floatValue());
        System.out.println(a.toString());
        System.out.println(JacksonUtil.encode2String(a));
    }

    @Test
    public void test3() {
        BigDecimal bigDecimal1 = BigDecimal.valueOf(1.0f);
        BigDecimal bigDecimal2 = BigDecimal.valueOf(1.0f);
        System.out.println(bigDecimal1.equals(bigDecimal2));
        System.out.println(JacksonUtil.encode2String(bigDecimal1));
    }

    @Test
    public void test4() {
        TestBean testBean = new TestBean(BigDecimal.valueOf(1.0f));
        System.out.println(JacksonUtil.encode2String(testBean));
    }
}
