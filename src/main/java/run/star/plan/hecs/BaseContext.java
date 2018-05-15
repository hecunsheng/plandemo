package run.star.plan.hecs;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Created by hecs on 2018/5/15.
 * desc:
 */
public class BaseContext implements Serializable {
    private static final long serialVersionUID = 8483747627358998118L;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
