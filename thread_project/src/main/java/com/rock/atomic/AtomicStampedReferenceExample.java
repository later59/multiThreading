package com.rock.atomic;

import com.rock.annnoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

@Slf4j
@ThreadSafe
public class AtomicStampedReferenceExample {

    //实例化一个AtomicStampedReference对象  值为：0  版本号为：1
    private static AtomicStampedReference<Integer> reference = new AtomicStampedReference<>(0, 1);

    public static void main(String[] args) {
        //第一个参数：期望的值
        //第二个参数：更新的值
        //第三个参数：期望的版本号
        //第四个参数：更新的版本号
        //如果当前值为0并且版本号为1 就将值更新成400 版本号更新成2
        reference.compareAndSet(0, 400, 1, 2);
        log.info("结果为：{}", reference.getReference());
        log.info("版本号为：{}", reference.getStamp());
    }
}
