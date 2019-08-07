package com.rock.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class CyclicBarrierExample3 {

    //声明一个只等待5个线程的CyclicBarrier
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5, () -> {
        log.info("当五个线程都到屏障之后，最先执行");
    });

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int threadNum = i;
            Thread.sleep(1000);
            exec.execute(() -> {
                try {
                    reace(threadNum);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        exec.shutdown();
    }

    private static void reace(int threadNum) throws Exception {
        Thread.sleep(1000);
        log.info("{},已准备好", threadNum);
        cyclicBarrier.await();
        log.info("{},继续执行", threadNum);
    }
}
