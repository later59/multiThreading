package com.rock.atomic;

import com.rock.annnoations.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

@Slf4j
@ThreadSafe
public class AtomicIntegerFieldUpdaterExample {

    //用来更新某个指定类的指定字段  字段必须是volatile修饰 并且不能是static修饰
    //创建一个AtomicIntegerFieldUpdater对象    newUpdater：第一个参数：监控的类   第二个参数：监控类中的哪个字段
    private static AtomicIntegerFieldUpdater<AtomicIntegerFieldUpdaterExample> updater = AtomicIntegerFieldUpdater.newUpdater(AtomicIntegerFieldUpdaterExample.class, "count");

    @Getter
    private volatile int count = 100;//记住必须使用volatile修饰

    public static void main(String[] args) {

        AtomicIntegerFieldUpdaterExample atomicExample = new AtomicIntegerFieldUpdaterExample();

        //如果该类的count字段的值为100 就更新成200
        if (updater.compareAndSet(atomicExample, 100, 200)) {
            log.info("count -------------->   {}", atomicExample.count);
        }
    }
}
