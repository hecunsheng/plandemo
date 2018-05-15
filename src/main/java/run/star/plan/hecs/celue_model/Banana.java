package run.star.plan.hecs.celue_model;

import run.star.plan.hecs.BaseContext;

/**
 * @Auther: hecs
 * @Date: 2018/5/15 16:30
 * @Description:
 */
public class Banana extends BaseContext{
    private String color;

    public Banana(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
