package com.zwc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

/**
 * @author: zhangwch
 * @create: 2020-09-21 16:27
 **/
@Component
@EnableAsync
public class SpringbootTest {

    @Autowired
    private AsyncTest asyncTest;

    public void AsyncTest() {
        asyncTest.printNum();
        for (int i = 0; i < 10; i++) {
            System.out.println("---------------------------");
        }
    }

    public static void main(String[] args) {
        // 通过xml配置将bean注入ioc容器再获取，遇到的问题：
        // 1、导入spring-beans jar包时，不要写版本号，springboot已经配置好会自动找到合适版本(pom里面导入时填写了版本号结果报错：版本冲突)
        // 2、获取bean时，bean对象名称首字母小写
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        SpringbootTest async = (SpringbootTest) context.getBean("springbootTest");
        async.AsyncTest();
    }
}
