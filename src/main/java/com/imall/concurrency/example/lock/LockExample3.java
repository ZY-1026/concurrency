package com.imall.concurrency.example.lock;

/**
 * ReentrantReadWriteLock
 */

import lombok.extern.slf4j.Slf4j;


import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Slf4j
public class LockExample3 {


//    TreeMap中的元素默认按照keys的自然排序排列。
    private final Map<String, Data> map = new TreeMap<>();
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final  Lock readlock = lock.readLock();
    private final Lock writelock = lock.writeLock();
    public Data get(String key){
        readlock.lock();
        try{
            return map.get(key);
        }finally {
            readlock.unlock();
        }
    }

    public Set<String > getAllKeys(){
        readlock.lock();
        try{
            return map.keySet();
        }finally {
            readlock.unlock();
        }
    }

    public Data put(String key, Data value){
        writelock.lock();
        try{
            return map.put(key, value);
        }finally {
            writelock.unlock();
        }
    }
    class Data{

    }
}
