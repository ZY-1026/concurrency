package com.imall.concurrency.example.singleton;

import com.imall.concurrency.annotations.NotRecommend;
import com.imall.concurrency.annotations.ThreadSafe;

/**
 * 安全发布对象
 */

/**
 * 线程安全的懒汉模式：
 * 单例的实例在第一次使用时创建
 **/
@ThreadSafe
@NotRecommend
public class SingletonExample3 {

    // 私有的构造函数
    private SingletonExample3() {

    }

    //    单例对象
    private static SingletonExample3 instance = null;

    //    静态工厂方法
    public static synchronized SingletonExample3 getInstance() { // 每次只有一个线程进入，开销大
        if (instance == null) {
            instance = new SingletonExample3();
        }
        return instance;
    }
}
