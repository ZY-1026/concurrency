package com.imall.concurrency.example.atomic;

import lombok.Getter;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * AtomicReferenceFiledUpdate的使用
 */

public class AtomicExample5 {
    private static AtomicIntegerFieldUpdater<AtomicExample5> updater = AtomicIntegerFieldUpdater.newUpdater(
            AtomicExample5.class, "count");

    @Getter
    public volatile int count = 100;

    public static void main(String[] args) {

        AtomicExample5 example5 = new AtomicExample5();
        if (updater.compareAndSet(example5, 100, 120))
            System.out.println(example5.count);
    }
}
