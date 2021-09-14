package run.star.plan.ISP.c;

import java.util.Map;

/**
 * @author hecs
 * @date 2021/7/20 13:38
 */
public class MySqlConfig implements Viewer{
    public MySqlConfig(ConfigSource configSource) {

    }

    @Override
    public String outputInPlainText() {
        return "MySqlConfig.outputInPlainText";
    }

    @Override
    public Map output() {
        return null;
    }
}
