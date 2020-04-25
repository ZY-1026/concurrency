package com.imall.concurrency.example.immutable;

import com.google.common.collect.Maps;
import com.imall.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;

/**
 * 利用java中Connections集合中的unmodifiableXXX方法可以将其变成不可变变量
 */

@Slf4j
@ThreadSafe
public class ImmutableExample2 {

    private static Map<Integer, Integer> map = Maps.newHashMap();

    static {
        map.put(1, 2);
        map.put(3, 4);
        map.put(5, 5);
        map = Collections.unmodifiableMap(map);
    }

    public static void main(String[] args) {
        map.put(1, 3); // 运行时会出错
        log.info("{}", map.get(1));
    }

}
