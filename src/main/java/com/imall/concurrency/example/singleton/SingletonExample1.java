package com.imall.concurrency.example.singleton;

import com.imall.concurrency.annotations.NotThreadSafe;

/**
 * 安全发布对象
 */

/**
 * 懒汉模式：
 * 单例的实例在第一次使用时创建
 **/
@NotThreadSafe
public class SingletonExample1 {

    // 私有的构造函数
    private SingletonExample1() {
    }

    //    单例对象
    private static SingletonExample1 instance = null;

    //    静态工厂方法
    public static SingletonExample1 getInstance() {
        if (instance == null) {
            instance = new SingletonExample1();
        }
        return instance;
    }
}
