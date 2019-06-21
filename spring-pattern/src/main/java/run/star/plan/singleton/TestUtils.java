package run.star.plan.singleton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.CountDownLatch;

/**
 * @Author: hecs
 * @Date: 2018/9/5 17:05
 * @Description:
 */
public class TestUtils {
    /**
     * 简单对比是否满足单例
     * @param obj1
     * @param obj2
     */
    public static void simpleTest(Object obj1, Object obj2) {
        System.out.println("---------------------------simpleTest---------------------------");
        System.out.println(obj1);
        System.out.println(obj2);
    }

    /**
     * 反射测试
     * @param className
     * @param obj1
     */
    public static void reflectTest(String className, Object obj1) {
        System.out.println("-------------------------reflectTest-----------------------------");
        System.out.println(System.currentTimeMillis() + ":正常对象>" + obj1);
        Class<?> clazz = null;
        try {
            clazz = Class.forName(className);
            Constructor<?>[] constructors = clazz.getDeclaredConstructors();
            for (Constructor constructor : constructors) {
                if (constructor.getParameterCount() == 0) {
                    constructor.setAccessible(true);
                    Object obj2 = constructor.newInstance();
                    System.out.println(System.currentTimeMillis() + ":反射对象>" + obj2);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 反序列化测试
     * @param obj1
     */
    public static void serializeTest(Object obj1) {
        System.out.println("--------------------------serializeTest----------------------------");
        Object obj2 = null;
        try {
            FileOutputStream fos = new FileOutputStream("d:/aaabbb.text");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(obj1);
            oos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            FileInputStream fis = new FileInputStream("d:/aaabbb.text");
            ObjectInputStream ois = new ObjectInputStream(fis);
            obj2 = ois.readObject();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        simpleTest(obj1, obj2);
    }

    /**
     * 测试线程安全问题
     */
    public static void threadSafetyTest(String className, int count) {
        System.out
            .println("--------------------------threadSafetyTest----------------------------");
        Method method = getGetInstanceMethod(className);
        CountDownLatch latch = new CountDownLatch(count);
        long star = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Object instance = null;
                    try {
                        instance = method.invoke(null);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    System.out.println(System.currentTimeMillis() + ":" + instance);
                }
            }.start();
            latch.countDown();
        }
        long end = System.currentTimeMillis();
        System.out.println("总耗时：" + (end - star));
    }

    /**
     * 性能测试 注册登记式最慢
     * @param className
     * @param count
     */
    public static void propertyTest(String className, int count) {
        Object obj = null;
        Method method = getGetInstanceMethod(className);
        long star = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            try {
                obj = method.invoke(null);
                //System.out.println(System.currentTimeMillis()+":"+obj);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                throw new RuntimeException("非法访问异常！");
            } catch (InvocationTargetException e) {
                e.printStackTrace();
                throw new RuntimeException("调用目标异常！");
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("总耗时:" + (end - star));
    }

    private static Method getGetInstanceMethod(String className) {
        try {
            Class<?> clazz = Class.forName(className);
            Method method = clazz.getMethod("getInstance");
            method.setAccessible(true);
            return method;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("class not found exception");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw new RuntimeException("no such method exception");
        }
    }

    /**
     *
     */
    public static void finalTest() {
        System.out.println("--------------------------finalTest----------------------------");
        String a = "hello2";
        String f = "hello2";
        final String b = "hello";
        String c = "hello";
        String d = b + 2;
        String e = c + 2;
        System.out.println(a == d);
        System.out.println(a == f);
        System.out.println(a == e);
        System.out.println(Integer.toHexString(c.hashCode()));
        c = c + 2;
        System.out.println(Integer.toHexString(c.hashCode()));
    }
}
