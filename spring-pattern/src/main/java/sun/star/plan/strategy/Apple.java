package sun.star.plan.strategy;

import sun.star.plan.common.BaseContext;

/**
 * Created by hecs on 2018/5/15.
 * desc:
 */
public class Apple extends BaseContext {

    private Integer weight;

    private String  color;

    public Apple(Integer weight, String color) {
        this.weight = weight;
        this.color = color;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
