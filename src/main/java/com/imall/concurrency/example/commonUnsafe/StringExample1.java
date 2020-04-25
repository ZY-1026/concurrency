package com.imall.concurrency.example.commonUnsafe;

import com.imall.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * StringBuilder:线程不安全
 */

@Slf4j
@NotThreadSafe
public class StringExample1 {

    public static int clientTotal = 5000;

    public static int threadTotal = 200;

    public static StringBuilder stringBuilder = new StringBuilder();

    private static void update() {
        stringBuilder.append("1");
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    update();
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();

        log.info("size= {}", stringBuilder.length());
    }
}
