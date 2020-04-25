package com.imall.concurrency.example.singleton;

import com.imall.concurrency.annotations.ThreadSafe;

/**
 * 饿汉模式：另一种写法
 * 单例的实例在类装载的时候创建
 **/

@ThreadSafe
public class SingletonExample6 {

    // 私有的构造函数
    private SingletonExample6() {

    }

    //    单例对象
    private static SingletonExample6 instance = null;

    /**
     * 通过静态块来实例化对象
     */
    static {
        instance = new SingletonExample6();
    }

    //    静态工厂方法
    public static SingletonExample6 getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        System.out.println(getInstance());
        System.out.println(getInstance());
    }
}