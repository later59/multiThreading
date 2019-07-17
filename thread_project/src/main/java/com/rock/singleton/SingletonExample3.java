package com.rock.singleton;

import com.rock.annnoations.ThreadSafe;

/**
 * 懒汉模式
 * 单例在第一次使用的时候创建
 */
@ThreadSafe
public class SingletonExample3 {

    //私有构造函数
    private SingletonExample3() {
    }

    private static SingletonExample3 instance;

    /**
     * 加入了synchronized关键字，每次只允许一个线程访问，实现了线程安全，
     * 但是造成了内存的开销
     * @return
     */
    //静态的工厂方法
    public static synchronized SingletonExample3 getInstance() {
        /**
         * 在单线程模式下，该方法不会出现问题
         * 在多线程模式下，可能会出现初始化两次对象
         * 当一个线程进行了if判断，为空。正准备初始化的时候，CPU资源被其他线程抢占，又进行了一次if判断
         * 这时候对象没有初始化，就会进入if判断，从而导致初始化两次对象
         */
        if (instance == null) {
            instance = new SingletonExample3();
        }
        return instance;
    }

}
