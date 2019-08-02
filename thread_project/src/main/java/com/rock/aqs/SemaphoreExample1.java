package com.rock.aqs;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
public class SemaphoreExample1 {

    public static void main(String[] args) throws InterruptedException {
        //请求线程总数
        int threadCount = 20;
        //声明一个线程池
        ExecutorService exec = Executors.newCachedThreadPool();
        //声明一个Semaphore，允许20个线程同时进行
        Semaphore semaphore = new Semaphore(2);

        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            exec.execute(() -> {
                try {
                    semaphore.acquire();//获取一个许可
                    implement(threadNum);
                    semaphore.release();//释放一个许可
                } catch (InterruptedException e) {
                    log.error("exception", e);
                }
            });
        }
        exec.shutdown();
    }

    private static void implement(int threadNum) throws InterruptedException {
        log.info("{}", threadNum);
        Thread.sleep(1000);
    }

}
