package run.star.plan.hecs.spring.design_pattern.proxy;

import java.io.Serializable;

public class FeiHui implements Person, Serializable {

    private String sex = "boy";
    private String name = "飞飞";

    @Override
    public void findLove() {
        System.out.println("----------------------------------------------run");
        System.out.println(this.toString());
        System.out.printf("find love");
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "FeiHui{" +
                "sex='" + sex + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
