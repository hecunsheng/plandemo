package run.star.plan.a_pattern_beautiful.ISP.c;

/**
 * @author hecs
 * @date 2021/7/20 13:37
 */
public class KafkaConfig implements Updater{

    public KafkaConfig(ConfigSource configSource) {

    }

    @Override
    public void update() {
        System.out.println("KafkaConfig update");
    }
}
