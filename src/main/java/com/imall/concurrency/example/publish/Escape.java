package com.imall.concurrency.example.publish;


import com.imall.concurrency.annotations.NotRecommend;
import com.imall.concurrency.annotations.NotThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * 对象逸出
 * 当一个对象还没有被构造完成时，就使得它被其他线程所见
 * 在构造器中启动一个线程，则隐藏的将this逸出给了该线程，但是实际上
 * 这个对象还没有完全构造成功
 */

@Slf4j
@NotThreadSafe
@NotRecommend
public class Escape {
    private int thisCanBeEscape = 0;
    public Escape(){
        new InnerClass();
    }

    public class InnerClass{
        public InnerClass(){
            log.info("{}", Escape.this.thisCanBeEscape );
        }
    }

    public static void main(String[] args) {
        new Escape();
    }

}
