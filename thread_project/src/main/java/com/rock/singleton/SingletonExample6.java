package com.rock.singleton;

import com.rock.annnoations.ThreadSafe;

/**
 * 饿汉模式
 */
@ThreadSafe
public class SingletonExample6 {

    //私有构造函数
    private SingletonExample6() {
    }

    //注意如果使用static代码块初始化，将对象声明现在静态代码块前面
    private static SingletonExample6 instance = null;

    static {
        instance = new SingletonExample6();
    }

    //静态工厂方法
    public SingletonExample6 getInstance() {
        return instance;
    }

}
