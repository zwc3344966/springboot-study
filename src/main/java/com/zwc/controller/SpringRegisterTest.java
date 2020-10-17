package com.zwc.controller;

import com.zwc.dao.RegisterDao;
import com.zwc.dao.impl.RegisterDaoImpl;
import com.zwc.service.RegisterService;
import com.zwc.service.impl.RegisterServiceImpl;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.Assert;

import javax.swing.*;

/**
 * @author: zhangwch
 * @create: 2020-08-26 10:59
 **/
public class SpringRegisterTest {

    private String name = "zwc";

    public String say() {
        return "hello"+ name;
    }

    @Test
    public void registerBean() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring.xml");
        // 获取spring bean 工厂 手动注册bean
        context.getBeanFactory().registerSingleton("zwc", new SpringRegisterTest());
        // 启动spring容器
        context.start();

        // 从上下文获取bean
        SpringRegisterTest zwc = context.getBean("zwc", SpringRegisterTest.class);
//        SpringRegisterTest zwc = (SpringRegisterTest) context.getBean("zwc");
        System.out.println(zwc.say());


//        RegisterServiceImpl zwc = (RegisterServiceImpl) context.getBean("register");
//        zwc.test();
    }

}
