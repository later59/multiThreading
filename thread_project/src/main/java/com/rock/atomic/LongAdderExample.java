package com.rock.atomic;

import com.rock.annnoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

@Slf4j
@ThreadSafe
public class LongAdderExample {
    // 请求总数
    public static int clientTotal = 5;

    // 同时并发执行的线程数
    public static int threadTotal = 1;

    public static LongAdder count = new LongAdder();

    public static void main(String[] args) throws Exception {
        //初始化一个线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        //设置同时可以并发多少个线程
        final Semaphore semaphore = new Semaphore(threadTotal);
        //设置请求总数
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    //获得
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (Exception e) {
                    log.error("exception", e);
                }
                countDownLatch.countDown();
            });
        }
        //等待所有线程执行完毕
        countDownLatch.await();
        executorService.shutdown();
        log.info("count:{}", count);
    }

    private static void add() {
        count.increment();
    }
}
