package run.star.plan.javabase.object.each;

/**
 * 既然Object作为Java中所有对象的超类，则根据继承关系的特点，以及向上转型机制，
 * Object可以接受任意数据类型对象的引用，例如在集合容器或者传参过程，
 * 不确定对象类型时可以使用Object：
 * @Author hecs
 * @Date 2021/10/18 14:13
 */
public class Each02 {

    public static void main(String[] args) {
        // 向上转型
        Object obj01 = new Each02Obj01("Java");
        System.out.println("obj01 = " + obj01);
        // 向下转型
        Each02Obj01 each02Obj01 = (Each02Obj01) obj01;
        System.out.println("each02Obj01.getName() = " + each02Obj01.getName());
    }
}
