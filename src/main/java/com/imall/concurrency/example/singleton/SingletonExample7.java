package com.imall.concurrency.example.singleton;


import com.imall.concurrency.annotations.Recommend;
import com.imall.concurrency.annotations.ThreadSafe;


/**
 * 枚举模式
 */
@ThreadSafe
@Recommend
public class SingletonExample7 {

    // 私有的构造函数
    private SingletonExample7() {
    }


    public static SingletonExample7 getInstance() {
        return Singleton.INSTANCE.getInstance();
    }

    private enum Singleton {
        INSTANCE;

        private SingletonExample7 singleton;

        //  jvm保证这个方法仅仅被执行一次
        Singleton() {
            singleton = new SingletonExample7();
        }

        public SingletonExample7 getInstance() {
            return singleton;
        }
    }
}