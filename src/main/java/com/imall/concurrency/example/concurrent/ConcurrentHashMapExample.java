package com.imall.concurrency.example.concurrent;

import com.imall.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.*;

/**
 * ConcurrentHashMap
 */

@Slf4j
@ThreadSafe
public class ConcurrentHashMapExample {
    public static int clientTotal = 5000;

    public static int threadTotal = 200; //200个线程

    private static Map<Integer, Integer> map = new ConcurrentHashMap<>();

    private static void update(int i) {
        map.put(i, i);
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            final int count = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    update(count);
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("size = {}", map.size());
    }
}
