package com.zwc.controller;

import org.springframework.util.Assert;

/**
 * @author: zhangwch
 * @create: 2020-09-09 10:38
 **/
public class ExceptionTest {

    public static void main(String[] args) {
        String name = null;
        Assert.notNull(name, "姓名不能为空");
    }
}
