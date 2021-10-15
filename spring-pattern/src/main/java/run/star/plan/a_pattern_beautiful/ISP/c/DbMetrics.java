package run.star.plan.a_pattern_beautiful.ISP.c;

import java.util.Map;

/**
 * @author hecs
 * @date 2021/7/27 16:31
 */
public class DbMetrics implements Viewer{
    @Override
    public String outputInPlainText() {
        return "DbMetrics";
    }

    @Override
    public Map output() {
        return null;
    }
}
