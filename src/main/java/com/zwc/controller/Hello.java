package com.zwc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zhangwch
 * @create: 2020-05-28 22:13
 **/
@RestController
@RequestMapping("hello")
public class Hello {

    private final Logger logger = LoggerFactory.getLogger(Hello.class);

    @RequestMapping(value = "hello", method = RequestMethod.POST)
    public String hello() {
        String method = "hello";
        logger.info("进入{}方法", method);
        logger.error("{}方法处理中", method);
        logger.debug("{}方法结束", method);
        return "hello";
    }
}
