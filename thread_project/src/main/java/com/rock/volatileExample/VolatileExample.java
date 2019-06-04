package com.rock.volatileExample;

import com.rock.annnoations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
@NotThreadSafe
public class VolatileExample {
    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行的线程数
    public static int threadTotal = 200;

    public volatile static int count = 0;

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
                    //
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (Exception e) {
                    log.error("exception", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("count:{}", count);
    }

    private static void add() {
        /**
         * count++ 的时候进行了三步操作
         * 从主内存中取出当前值
         * 当前值加一
         * 写回主存
         *
         * 这时候可能两个线程同时从主内存中取了值，就会丢失数据
         */
        count++;
    }
}
