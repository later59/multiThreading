package com.rock.atomic;

import com.rock.annnoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

@Slf4j
@ThreadSafe
public class AtomicIntegerArrayExample {
    private static AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(10);

    public static void main(String[] args) {
        atomicIntegerArray.addAndGet(0,10);
        log.info("第一个：{}",atomicIntegerArray.get(0));
        //如果数组中的第一个数，当前值为10 就更改为20
        boolean b = atomicIntegerArray.compareAndSet(0, 10, 100);
        log.info("更改的结果为：{}",b);
    }
}
