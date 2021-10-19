package run.star.plan.javabase.object.concept;

import lombok.Getter;
import lombok.Setter;

/**
 * 特征之封装
 * 将结构、数据、操作封装在对象实体中，使用时可以不关注对象内部结构，
 * 只能访问开放权限的功能入口，从而降低程序耦合程度，提供安全性和可持续维护性。
 * @Author hecs
 * @Date 2021/10/15 19:52
 */
public class Concept01 {

    public static void main(String[] args) {
        Student student = new Student("张三","高三",29f);
        student.conclusion();
    }

    @Getter
    static class Student {
        private String name ;
        private String grade ;
        private Float score ;
        public Student(String name, String grade, Float score) {
            this.name = name;
            this.grade = grade;
            this.score = score;
        }
        public void conclusion (){
            System.out.println("姓名："+this.getName());
            System.out.println("年级："+this.getGrade());
            System.out.println("分数："+this.getGrade());
            if (this.getScore() >= 100.0f){
                System.out.println("评语：本学期优等生");
            } else {
                System.out.println("评语：本学期潜力股");
            }
        }
    }
}
