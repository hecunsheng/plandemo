package run.star.plan.thread.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池饱和拒绝策略方案
 * A handler for rejected tasks that adds the specified element to this queue,
 * waiting if necessary for space to become available.
 * @author hecs
 */
public class ForceQueuePolicy implements RejectedExecutionHandler {

    private final Logger LOGGER = LoggerFactory.getLogger(ForceQueuePolicy.class);

    /**
     * @see RejectedExecutionHandler#rejectedExecution(Runnable, ThreadPoolExecutor)
     */
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        try {
            executor.getQueue().put(r);
        } catch (InterruptedException e) {
            LOGGER.error("BlockingQueue put element error", e);
        }

    }
}
