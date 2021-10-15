package run.star.plan.a_pattern_beautiful.ISP.c;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author hecs
 * @date 2021/7/20 13:41
 */
public class ScheduledUpdater {


    private final ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    ;
    private long initialDelayInSeconds;
    private long periodInSeconds;
    private Updater updater;

    public ScheduledUpdater(Updater updater, long initialDelayInSeconds, long periodInSeconds) {
        this.updater = updater;
        this.initialDelayInSeconds = initialDelayInSeconds;
        this.periodInSeconds = periodInSeconds;
    }

    public void run() {
        executor.scheduleAtFixedRate(
                () -> updater.update(),
                this.initialDelayInSeconds,
                this.periodInSeconds,
                TimeUnit.SECONDS);
    }
}
