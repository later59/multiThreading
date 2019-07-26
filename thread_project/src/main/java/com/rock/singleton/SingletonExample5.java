package com.rock.singleton;

import com.rock.annnoations.NotThreadSafe;
import com.rock.annnoations.ThreadSafe;

/**
 * 懒汉模式  -> 双重同步锁单例模式
 * 单例在第一次使用的时候创建
 */
@ThreadSafe
public class SingletonExample5 {

    //私有构造函数
    private SingletonExample5() {
    }

    //添加volatile关键字，禁止指令重排  涉及到volatile写操作
    private volatile static SingletonExample5 instance;


    //静态的工厂方法
    public static SingletonExample5 getInstance() {
        /**
         * 初始化对象的步骤  instance = new SingletonExample4();
         * 1、分配对象内存空间
         * 2、初始化对象
         * 3、设置instance指向刚分配的地址
         */
        if (instance == null) {//双重检测机制
            synchronized (SingletonExample5.class){//同步锁
                instance = new SingletonExample5();
            }
        }
        return instance;
    }

}
