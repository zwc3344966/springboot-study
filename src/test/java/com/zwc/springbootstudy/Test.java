package com.zwc.springbootstudy;

import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author: zhangwch
 * @create: 2020-09-12 17:24
 **/
public class Test {

    public static void main(String[] args) {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("11", "11");
        map.put("22", "22");
        map.put("33", "33");
        map.forEach((k, v) -> {
            System.out.println("k = [" + k + "];v = ["+ v +"]");
        });
    }
}
