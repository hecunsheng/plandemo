package run.star.plan.BATJ;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * @author hecs
 * @date 2020-08-31 15:08
 */
public class ReferenceDemo {

    static Object obj = new Object();

    public static void main(String[] args) throws InterruptedException {
        //强引用
        Object strongObj = obj;
        obj = null;
//        strongObj = null;
        System.gc();
        System.out.println("gc之后" + strongObj);

        //软引用
        Object softRef = new Object();
        SoftReference softReference = new SoftReference(softRef);
        softRef = null;
//        softReference = null;
        System.gc();
        System.out.println("gc之后" + softReference.get());

        //弱引用
        String str = "123"; //gc不回收常量池 跟随对象生命周期
        Object weakObj = new Object();
        WeakReference weakRef = new WeakReference(weakObj);
//        str = null;
        weakObj = null;
//        System.gc();
        System.out.println("gc之后" + weakRef.get());

        //虚引用 对象为空,触发gc,  监控对象回收 回收了触发
        ReferenceQueue queue = new ReferenceQueue();
        Object phantomRefObj = new Object();
        PhantomReference phantomRef = new PhantomReference(phantomRefObj, queue);

        phantomRefObj = null;
        System.gc();
        System.out.println(phantomRef.get());
        System.out.println(queue.poll());
    }
}
