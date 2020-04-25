package com.imall.concurrency.example.immutable;

import com.google.common.collect.Maps;
import com.imall.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;


@Slf4j
@NotThreadSafe
public class ImmutableExample1 {

    private final static Integer a = 1;
    private final static String b = "2";
    private final static Map<Integer, Integer> map = Maps.newHashMap();

    static {
        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 5);
    }

    public static void main(String[] args) {
//        a =2;
//        b = "3";
//        map = Maps.newHashMap();

        /**
         * final在修饰引用类型的变量时，
         * 不允许将该变量指向其他新的变量，
         * 但是允许修改变量的值
         */
        map.put(1, 3);
        log.info("{}", map.get(1));
    }


    private void test(final int a){
//        a =1; // 不允许修改
    }
}
