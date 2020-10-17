package com.zwc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zhangwch
 * @create: 2020-05-28 22:13
 **/
@RestController
@RequestMapping("hello")
public class Hello {

    @RequestMapping("hello")
    public String hello() {
        System.out.println("===进入hello方法===");
        System.out.println("===进入hello方法===");

        return "hello";
    }
}
