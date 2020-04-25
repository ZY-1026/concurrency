package com.imall.concurrency.example.singleton;

import com.imall.concurrency.annotations.ThreadSafe;

/**
 * 饿汉模式：
 * 单例的实例在类装载的时候创建
 **/

@ThreadSafe
public class SingletonExample2 {

    // 私有的构造函数
    private SingletonExample2() {

    }

    //    单例对象：通过静态对象来实例化对象
    private static SingletonExample2 instance = new SingletonExample2();

    //    静态工厂方法
    public static SingletonExample2 getInstance() {
        return instance;
    }
}