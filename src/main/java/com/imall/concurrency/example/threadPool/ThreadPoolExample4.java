package com.imall.concurrency.example.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ThreadPoolExample4 {
    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);

//        for (int i = 0; i < 10; i++) {
//            final int index = i;
//            executorService.execute(() -> log.info("task:{}", index));
//        }
//
//        executorService.schedule(new Runnable() {
//            @Override
//            public void run() {
//                log.warn("Schedule run");
//            }
//        }, 3, TimeUnit.SECONDS);

        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                log.warn("Schedule run");
            }
            // 每个三秒执行一次
        }, 1, 3, TimeUnit.SECONDS);
//        executorService.shutdown();

        // Timer也可以达到相同的效果
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                log.warn("Timer run");
            }
        }, new Date(), 5 * 1000);
    }
}
