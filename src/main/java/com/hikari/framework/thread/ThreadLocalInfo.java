package com.hikari.framework.thread;

/**
 * ThreadLocal
 *  保存用户线程信息
 * @author lkc39miku_cn
 */
public class ThreadLocalInfo {
    private static final ThreadLocal<Object> threadLocal = new ThreadLocal<>();

    public static void set(Object object) {
        threadLocal.set(object);
    }

    public static Object get() {
        return threadLocal.get();
    }

    public static void remove() {
        threadLocal.remove();
    }
}
