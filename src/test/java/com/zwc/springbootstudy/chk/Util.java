package com.zwc.springbootstudy.chk;

import java.lang.reflect.Field;

/**
 * @author: zhangwch
 * @create: 2020-09-18 09:31
 **/
public class Util {

    /**
     * 通过反射拿到属性名称个value
     * @param baseDO
     * @throws IllegalAccessException
     */
    public static void chkParam(BaseDO baseDO) throws IllegalAccessException {
        Field[] fields = baseDO.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(ChkParam.class)) {
                field.setAccessible(true);
                ChkParam annotation = field.getAnnotation(ChkParam.class);
                System.out.println("field = [" + annotation.name() + "|" + field.getName() + "] : value = [" + field.get(baseDO) + "]");
            }
        }
    }

    public static void main(String[] args) throws IllegalAccessException {
        chkParam(new ReqChkParam("1", "zwc", "25"));
    }
}
