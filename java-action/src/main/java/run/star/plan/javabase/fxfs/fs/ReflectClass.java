package run.star.plan.javabase.fxfs.fs;

import java.lang.reflect.Constructor;
import java.util.Arrays;

/**
 * @Author hecs
 * @Date 2021/11/12 20:49
 */
public class ReflectClass {
//    反射的类库
//    java.lang.Class：Class类
//    java.lang.reflect.Constructor：构造器
//    java.lang.reflect.Field：属性
//    java.lang.reflect.Method：方法

    public static void main(String[] args) throws Exception {
        //// Class对象回去
        UserDTO userDTO1 = new UserDTO(1, "hecs");
        Class aClass1 = userDTO1.getClass();
        Class aClass2 = Class.forName("run.star.plan.javabase.fxfs.fs.UserDTO");
        Class aClass3 = UserDTO.class;
        System.out.println(UserDTO.class.getName());
        System.out.println("aClass1==aClass2?" + (aClass1 == aClass2));
        System.out.println("aClass2==aClass3?" + (aClass2 == aClass3));
        // 类型创建和判断
        Object object = UserDTO.class.newInstance();
        System.out.println("类型：" + (object instanceof UserDTO));
        System.out.println("类型：" + (aClass3.isInstance(userDTO1)));
        System.out.println("=======================");

        Class userClass = UserDTO.class;
        // 读取公共构造方法
        Constructor[] constructors = userClass.getConstructors();
        Arrays.stream(constructors).forEach(System.out::println);
        System.out.println("=======================");
        // 读取指定私有构造方法
        Constructor privateCon = userClass.getDeclaredConstructor(Integer.class);
        System.out.println(privateCon);
        System.out.println("=======================");
        // 读取全部构造方法
        constructors = userClass.getDeclaredConstructors();
        Arrays.stream(constructors).forEach(System.out::println);
        System.out.println("=======================");
        // 调用公共构造方法创建对象
        Constructor pubCon = userClass.getConstructor(Integer.class, String.class);
        Object pubUser = pubCon.newInstance(1, "hello");
        System.out.println(pubUser);
        System.out.println("=======================");
        // 调用私有构造方法创建对象
        Constructor priCon = userClass.getDeclaredConstructor(Integer.class);
        // 忽略private权限修饰符
        priCon.setAccessible(Boolean.TRUE);
        Object priUser = priCon.newInstance(2);
        System.out.println(pubUser + "\n" + priUser);
        System.out.println("=======================");
    }
}
