package com.imall.concurrency.example.sync;


import com.imall.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@ThreadSafe
public class SynchronizedCount {

    public static int clientTotal = 5000;

    public static int threadTotal = 200;

    public static int count = 0;

    private synchronized static void add() {
        count++;
    }

    public static void main(String[] args) throws InterruptedException {
        // 创建一个线程池
        ExecutorService executorService = Executors.newCachedThreadPool();

        //Semaphore 是 synchronized 的加强版，作用是控制线程的并发数量
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("count = {} ", count);
    }
}
