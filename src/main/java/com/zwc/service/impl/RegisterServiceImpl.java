package com.zwc.service.impl;

import com.zwc.dao.RegisterDao;
import com.zwc.service.RegisterService;

/**
 * @author: zhangwch
 * @create: 2020-09-01 09:08
 **/
public class RegisterServiceImpl implements RegisterService {
    private RegisterDao registerDao;

    // setting注入
    public void setRegisterDao(RegisterDao registerDao) {
        this.registerDao = registerDao;
    }

    @Override
    public void test() {
        System.out.println("注入测试");
        System.out.println(registerDao.test());
    }
}
