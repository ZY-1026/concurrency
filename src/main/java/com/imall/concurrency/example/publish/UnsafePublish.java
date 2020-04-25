package com.imall.concurrency.example.publish;

/**
 * 发布对象
 */

import com.imall.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
@NotThreadSafe
public class UnsafePublish {
    private String[] states = {"a", "b", "c"};
    public String[] getStates(){
        return states;
    }

    public static void main(String[] args) {
        /**
         * 该对象是不安全的，因为线程可以修改对象中status属性的值
         */
        UnsafePublish unsafePublish = new UnsafePublish();
        log.info("{}", Arrays.toString(unsafePublish.getStates()));

        unsafePublish.getStates()[0] = "d";
        log.info("{}", Arrays.toString(unsafePublish.getStates()));
    }
}
