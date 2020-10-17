package com.zwc.controller;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author: zhangwch
 * @create: 2020-09-21 16:23
 **/
@Component
public class AsyncTest {

    @Async
    public void printNum() {
        for (int i = 0; i < 90; i++) {
            System.out.println("args = [" + i + "]");
        }
    }
}
