package com.rock.aqs;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
public class SemaphoreExample2 {

    public static void main(String[] args) throws InterruptedException {
        //请求线程总数
        int threadCount = 20;
        //声明一个线程池
        ExecutorService exec = Executors.newCachedThreadPool();
        //声明一个有2个许可的Semaphore，如果许可被使用完就不会
        Semaphore semaphore = new Semaphore(2);

        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            exec.execute(() -> {
                try {
                    semaphore.acquire(2);//获取多个许可
                    implement(threadNum);
                    semaphore.release(2);//释放多个许可
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
