package com.imall.concurrency.example.syncContainer;

import java.util.Iterator;
import java.util.Vector;

/**
 *在使用foreach或者迭代器进行集合元素遍历的时候，不要在进行遍历的时候对集合元素进行操作，
 * 否则会抛出异常，建议将需要操作的元素先保存下来，当遍历结束之后，在进行元素操作。
 */
public class VectorExample3 {

    /**
     * 运行时异常：java.util.ConcurrentModificationException
     */
    private static void test1(Vector<Integer> v1) { //foreach
        for (Integer v : v1) {
            if (v.equals(3)) {
                v1.remove(v);
            }
        }
    }


    /**
     * 运行时异常：java.util.ConcurrentModificationException
     */
    private static void test2(Vector<Integer> v1) { //Iterator
        Iterator<Integer> iterator = v1.iterator();
        while (iterator.hasNext()) {
            Integer i = iterator.next();
            if (i.equals(3)) {
                v1.remove(i);
            }
        }
    }


    /**
     * 运行成功
     */
    private static void test3(Vector<Integer> v1) { //for
        for (int i = 0; i < v1.size(); i++) {
            if (v1.get(i).equals(3)) {
                v1.remove(i);
            }
        }
    }

    public static void main(String[] args) {
        Vector<Integer> vector = new Vector<>();
        vector.add(1);
        vector.add(2);
        vector.add(3);
        test3(vector);
    }
}
