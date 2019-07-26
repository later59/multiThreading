package com.rock.threadLocal;

public class RequestHolder {

    private final static ThreadLocal<Long> requestHolder = new ThreadLocal<>();

    public static void add(Long id) {
        requestHolder.set(id);
    }

    public static Long getId() {
        return requestHolder.get();
    }

    /**
     * 必须提供一个移除的方法
     * 如果不做移除，会造成内存泄露，数据不会释放掉
     * 该类会一直伴随的项目，之后项目重启的时候才会清除
     */
    public static void removeId() {
        requestHolder.remove();
    }
}
