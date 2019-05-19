package com.rock.atomic;

import com.rock.annnoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@ThreadSafe
public class AtomicReferenceExample {

    private static AtomicReference<Integer> count = new AtomicReference<>(0);

    public static void main(String[] args) {
        //如果值是0就更新成2
        count.compareAndSet(0, 2);
        //如果值为2就更新成4
        count.compareAndSet(2, 4);
        count.compareAndSet(5, 10);
        log.info("count ------------------> {}", count.get());
    }
}
