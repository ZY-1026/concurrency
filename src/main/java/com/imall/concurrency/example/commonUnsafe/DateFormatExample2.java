package com.imall.concurrency.example.commonUnsafe;

import com.imall.concurrency.annotations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * SimpleDateFormat：采用栈封闭的方式使得其变成线程安全
 */

@Slf4j
@ThreadSafe
public class DateFormatExample2 {


    public static int clientTotal = 5000;

    public static int threadTotal = 200;


    private static void update() {
        try {
            SimpleDateFormat simpleDateFormat =
                    new SimpleDateFormat("yyyyMMdd");
            simpleDateFormat.parse("20200407");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) { //lambda表达式
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
    }
}
