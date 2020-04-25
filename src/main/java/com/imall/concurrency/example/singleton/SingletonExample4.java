package com.imall.concurrency.example.singleton;

import com.imall.concurrency.annotations.NotThreadSafe;

/**
 * 安全发布对象
 */

/**
 * 懒汉模式 =》双重同步锁单例模式：
 * 单例的实例在第一次使用时创建
 **/

@NotThreadSafe
public class SingletonExample4 {

    // 私有的构造函数
    private SingletonExample4() {

    }

    //    单例对象
    private static SingletonExample4 instance = null;

    //    静态工厂方法
    public static SingletonExample4 getInstance() {
        if (instance == null) { // 双重检测机制
            synchronized (SingletonExample4.class) { // 同步锁
                if (instance == null) {
                    /**
                     * 过程
                     * 1、memory=allocate() 分配对象内存空间
                     * 2、ctorInstance() 初始化对象
                     * 3、instance=memory 设置instance指向刚分配的内存
                     */

                    /**
                     * 在实例化对象过程中，由于JVM和cpu优化，可能会发生上述指令的重排序
                     * 所以会导致线程不安全
                     *   1、memory=allocate() 分配对象内存空间
                     *   3、instance=memory 设置instance指向刚分配的内存
                     *   2、ctorInstance() 初始化对象
                     */
                    instance = new SingletonExample4();
                }
            }
        }
        return instance;
    }
}
