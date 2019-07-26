package com.rock.singleton;

import com.rock.annnoations.Recommend;
import com.rock.annnoations.ThreadSafe;


@ThreadSafe
@Recommend
public class SingletonExample7 {

    //私有构造函数
    private SingletonExample7() {
    }

    public static SingletonExample7 getInstance() {
        return Singlethon.INSTANCE.getInstance();
    }

    private enum Singlethon {
        INSTANCE;

        private SingletonExample7 singleton;

        //JVM保证这个方法绝对只执行一次
        Singlethon() {
            singleton = new SingletonExample7();
        }

        public SingletonExample7 getInstance() {
            return singleton;
        }
    }
}
