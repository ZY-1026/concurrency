package com.imall.concurrency.example.singleton;

import com.imall.concurrency.annotations.ThreadSafe;

/**
 * 安全发布对象
 */

/**
 * 懒汉模式 =》正确的双重同步锁单例模式：
 * 单例的实例在第一次使用时创建
 **/

@ThreadSafe
public class SingletonExample5 {

    // 私有的构造函数
    private SingletonExample5() {

    }

    //    单例对象
    private volatile static SingletonExample5 instance = null; //限制其进行指令重排

    //    静态工厂方法
    public static SingletonExample5 getInstance() {
        if (instance == null) { // 双重检测机制
            synchronized (SingletonExample5.class){ // 同步锁
                if(instance==null){
                    /**
                     * 过程
                     * 1、memory=allocate() 分配对象内存空间
                     * 2、ctorInstance() 初始化对象
                     * 3、instance=memory 设置instance指向刚分配的内存
                     */

                    instance = new SingletonExample5();
                }
            }
        }
        return instance;
    }
}
