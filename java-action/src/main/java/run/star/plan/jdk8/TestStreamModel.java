package run.star.plan.jdk8;

import lombok.Data;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @description:
 * @author: hecs
 * @date: 2019-01-17 19:03
 **/
@Data
public class TestStreamModel {
    private int id;

    private String name;

    private int grade;

    private int classes;

    private double score;

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TestStreamModel that = (TestStreamModel) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(id, that.id)
                .append(grade, that.grade)
                .append(classes, that.classes)
                .append(score, that.score)
                .append(name, that.name)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(id)
                .append(name)
                .append(grade)
                .append(classes)
                .append(score)
                .toHashCode();
    }
}
