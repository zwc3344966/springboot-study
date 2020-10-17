package com.zwc.dao.impl;

import com.zwc.dao.RegisterDao;

/**
 * @author: zhangwch
 * @create: 2020-09-01 09:36
 **/
public class RegisterDaoImpl implements RegisterDao {
    @Override
    public String test() {
        System.out.println("dao注入测试");
        return "success";
    }
}
