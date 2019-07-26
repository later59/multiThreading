package com.rock.singleton;

import com.rock.annnoations.NotThreadSafe;

/**
 * 懒汉模式  -> 双重同步锁单例模式
 * 单例在第一次使用的时候创建
 */
@NotThreadSafe
public class SingletonExample4 {

    //私有构造函数
    private SingletonExample4() {
    }

    private static SingletonExample4 instance;


    //静态的工厂方法
    public static  SingletonExample4 getInstance() {
        /**
         * 初始化对象的步骤  instance = new SingletonExample4();
         * 1、分配对象内存空间
         * 2、初始化对象
         * 3、设置instance指向刚分配的地址
         *
         * 如果发生指令重排
         * 1、分配对象内存空间
         * 3、设置instance指向刚分配的地址
         * 2、初始化对象
         *
         * 如果有两个线程A，B，调用该方法，如果线程A执行到3，指向了分配的地址，
         * 但是没有初始化对象，线程B就直接返回了instance就可能出现问题
         */
        if (instance == null) {//双重检测机制
            synchronized (SingletonExample4.class){//同步锁
                instance = new SingletonExample4();
            }
        }
        return instance;
    }

}
