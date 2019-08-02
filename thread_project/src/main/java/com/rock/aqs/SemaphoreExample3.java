package com.rock.aqs;


import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

@Slf4j
public class SemaphoreExample3 {

    public static void main(String[] args) throws InterruptedException {
        //请求线程总数
        int threadCount = 20;
        //声明一个线程池
        ExecutorService exec = Executors.newCachedThreadPool();
        //声明一个有2个许可的Semaphore，如果许可被使用完就不会
        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            exec.execute(() -> {
                try {
                    /*if (semaphore.tryAcquire(3)) {//尝试获取3个许可，如果没有获取到就不执行
                        implement(threadNum);
                        semaphore.release(3);
                    }*/

                    //尝试获取一个许可，如果3秒内没有获取到则不执行
                    if (semaphore.tryAcquire(3000, TimeUnit.MILLISECONDS)) {
                        implement(threadNum);
                        semaphore.release(3);
                    }
                    //释放一个许可
                } catch (InterruptedException e) {
                    log.error("exception", e);
                }
            });
        }
        exec.shutdown();
    }

    private static void implement(int threadNum) throws InterruptedException {
        log.info("{}", threadNum);
        Thread.sleep(10000);
    }

}
