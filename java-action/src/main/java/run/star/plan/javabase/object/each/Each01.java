package run.star.plan.javabase.object.each;

/**
 * 显式扩展
 * 结论验证
 * 既然Object作为所有类的父级别的类，则不需要在显式的添加继承关系，Each01编译期就会提示移除冗余。
 * @Author hecs
 * @Date 2021/10/18 14:11
 */
public class Each01 extends Object{
//    javap -c Each01.class
//    Compiled from "Each01.java"
//    public class com.base.object.each.Each01 {
//  public com.base.object.each.Each01();
//        Code:
//        0: aload_0
//        1: invokespecial #1 // Method java/lang/Object."<init>":()V
//        4: return
//    }
//    JVM怎样使Native Method跑起来：
//    我们知道，当一个类第一次被使用到时，这个类的字节码会被加载到内存，并且只会回载一次。
//    在这个被加载的字节码的入口维持着一个该类所有方法描述符的list，
//    这些方法描述符包含这样一些信息：方法代码存于何处，它有哪些参数，方法的描述符（public之类）等等。
//    如果一个方法描述符内有native，这个描述符块将有一个指向该方法的实现的指针。
//    这些实现在一些DLL文件内，但是它们会被操作系统加载到java程序的地址空间。
//    当一个带有本地方法的类被加载时，其相关的DLL并未被加载，因此指向方法实现的指针并不会被设置。
//    当本地方法被调用之前，这些DLL才会被加载，这是通过调用java.system.loadLibrary()实现的。
//    最后需要提示的是，使用本地方法是有开销的，它丧失了java的很多好处。如果别无选择，我们可以选择使用本地方法
}
