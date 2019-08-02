package com.rock.aqs;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CountDownLatchExample2 {


    public static void main(String[] args) throws InterruptedException {
        //请求线程总数
        int threadCount = 200;
        //声明一个线程池
        ExecutorService exec = Executors.newCachedThreadPool();
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            exec.execute(() -> {
                try {
                    implement(threadNum);
                } catch (InterruptedException e) {
                    log.error("exception", e);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        //等待10毫秒，就执行下面的代码
        countDownLatch.await(10, TimeUnit.MILLISECONDS);
        log.info("已完成");
        exec.shutdown();
    }

    private static void implement(int threadNum) throws InterruptedException {
        Thread.sleep(100);
        log.info("{}", threadNum);
    }

}
