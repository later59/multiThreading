package com.rock.singleton;

import com.rock.annnoations.ThreadSafe;

/**
 * 饿汉模式
 * 单例实例在类装载时进行创建
 *
 *
 * 饿汉模式因为是在类装载的时创建，如果构造函数中有很多业务处理
 * 会导致类装载过慢，从而有性能问题。
 * 如果该类在装载过后又没有使用它，就会导致资源的浪费
 */
@ThreadSafe
public class SingletonExample2 {

    //私有构造函数
    private SingletonExample2() {
    }

    //单例对象
    private static SingletonExample2 instance = new SingletonExample2();

    //静态工厂方法
    public SingletonExample2 getInstance() {
        return instance;
    }
}
