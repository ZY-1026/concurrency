package com.imall.concurrency.example.commonUnsafe;

import com.imall.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * StringBuffer:线程安全
 */
@Slf4j
@ThreadSafe
public class StringExample2 {

    public static int clientTotal = 5000;

    public static int threadTotal = 200;

    public static StringBuffer stringBuffer = new StringBuffer();

    private static void update() {
        stringBuffer.append("1");
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

        log.info("size= {}", stringBuffer.length());
    }
}
